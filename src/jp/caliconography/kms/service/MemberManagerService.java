package jp.caliconography.kms.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import jp.caliconography.kms.meta.MemberMeta;
import jp.caliconography.kms.model.Member;
import jp.caliconography.kms.model.ProcessResult;
import jp.caliconography.kms.model.ProcessResult.ProcessStatus;
import jp.caliconography.util.PasswordCreator;
import jp.sf.orangesignal.csv.Csv;
import jp.sf.orangesignal.csv.CsvConfig;
import jp.sf.orangesignal.csv.handlers.ColumnNameMapListHandler;

import org.slim3.controller.upload.FileItem;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.mail.MailService.Message;
import com.google.appengine.api.mail.MailServiceFactory;
import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.SearchServiceFactory;

public class MemberManagerService {

	private MemberMeta memberMeta = new MemberMeta();

	private static final Index INDEX = SearchServiceFactory.getSearchService().getIndex(IndexSpec.newBuilder().setName("shared_index"));

	private static final Logger LOG = Logger.getLogger(MemberManagerService.class.getName());

	public Member addMember(Map<String, Object> input) {

		// データストア登録
		Member member = new Member();
		BeanUtil.copy(input, member);
		Transaction tx = Datastore.beginTransaction();
		Datastore.put(member);

		// 全文検索用登録
		Document.Builder docBuilder = Document.newBuilder().addField(Field.newBuilder().setName(memberMeta.gplusId.getAttributeName()).setText(member.getGplusId())).addField(Field.newBuilder().setName(memberMeta.mail.getAttributeName()).setText(member.getMail())).addField(Field.newBuilder().setName(memberMeta.twitterId.getAttributeName()).setText(member.getTwitterId()));
		Document doc = docBuilder.build();
		LOG.info("Adding document:\n" + doc.toString());
		try {
			INDEX.put(doc);
		} catch (RuntimeException e) {
			LOG.log(Level.SEVERE, "Failed to put " + doc, e);
			return null;
		}
		tx.commit();

		return member;
	}

	public ProcessResult apply(Map<String, Object> input) {
		
		// データストア登録
		Member member = new Member();
		BeanUtil.copy(input, member);
		member.setApproved(false);
		Transaction tx = Datastore.beginTransaction();
		Datastore.put(member);
		
		// 全文検索用登録
		Document.Builder docBuilder = Document.newBuilder()
				.addField(Field.newBuilder().setName(memberMeta.gplusId.getAttributeName()).setText(member.getGplusId()))
				.addField(Field.newBuilder().setName(memberMeta.mail.getAttributeName()).setText(member.getMail()))
				.addField(Field.newBuilder().setName(memberMeta.twitterId.getAttributeName()).setText(member.getTwitterId()))
				.addField(Field.newBuilder().setName(memberMeta.approved.getAttributeName()).setText(Boolean.toString(member.isApproved())));
		Document doc = docBuilder.build();
		LOG.info("Adding document:\n" + doc.toString());
		try {
			INDEX.put(doc);
		} catch (RuntimeException e) {
			LOG.log(Level.SEVERE, "Failed to put " + doc, e);
			return null;
		}
		tx.commit();

		return new ProcessResult(ProcessStatus.SUCCESS, "入里申請しました！G+での連絡をお待ちください。<br/>数日以内に連絡がない場合は、G+でハッシュタグ<a href='https://plus.google.com/u/0/s/%23%E9%9A%A0%E6%AD%A6%E5%A3%AB%E4%B9%83%E9%87%8C%E5%85%A5%E9%87%8C%E7%94%B3%E8%AB%8B' target='_blank'>#隠武士の里入里申請</a>を付けてご連絡ください。", 1);
	}

	public ProcessResult approve(Map<String, Object> input) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		Member member = new Member();
		BeanUtil.copy(input, member);
		Member storedMember = Datastore.query(memberMeta).filter(memberMeta.gplusId.equal(member.getGplusId())).asSingle();
		String password = null;
		StringBuilder body = new StringBuilder();

		if (member.isApproved()) {
			storedMember.setApproved(false);
		} else {
			storedMember.setApproved(true);
			password = PasswordCreator.createRandomPassword(10);
			storedMember.setPassword(password);

			body.append(storedMember.getGplusName() + "さん\r\n\r\n");
			body.append("隠れモノノフの里への加入が承認されました！\r\n\r\n");
			body.append("パスワード：" + password + "\r\n\r\n\r\n");
			body.append("----------------------------------\r\n");
			body.append("隠れモノノフの里：http://momoclo.in\r\n");
			body.append("隠れモノノフの里 メンバー管理：http://kmscld.appspot.com/MemberManager\r\n");
		}
		Transaction tx = Datastore.beginTransaction();
		Datastore.put(storedMember);
		tx.commit();

		// メール送信はコミット後
		if (member.isApproved()) {

		} else {
			// メール送信
			Message msg = new Message();
			msg.setSender("caliconography@gmail.com");
			msg.setTo(storedMember.getMail());
			msg.setSubject("[隠れモノノフの里]加入承認通知");
			msg.setTextBody(body.toString());
			MailServiceFactory.getMailService().send(msg);
		}

		return new ProcessResult(ProcessStatus.SUCCESS, storedMember.isApproved() ? "承認しました！" : "承認を取り消しました！", 1);
	}

	public ProcessResult delete(Map<String, Object> input) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
		Member member = new Member();
		BeanUtil.copy(input, member);
		Member storedMember = Datastore.query(memberMeta).filter(memberMeta.gplusId.equal(member.getGplusId())).asSingle();

		storedMember.setDeletedDate(new Date());
		Transaction tx = Datastore.beginTransaction();
		Datastore.put(storedMember);
		tx.commit();

		return new ProcessResult(ProcessStatus.SUCCESS, "削除しました！", 1);
	}

	public List<Member> getMemberList() {
		return Datastore.query(memberMeta).filter(memberMeta.deletedDate.equal(null)).sort(memberMeta.gplusId.asc).asList();
	}

	public Member getMember(String mail) {
		return Datastore.query(memberMeta).filter(memberMeta.mail.equal(mail)).asSingle();
	}

	public List<Member> searchMember(String query, int limit) {
		FullTextSearchService ftsService = new FullTextSearchService();
		Results<ScoredDocument> results = ftsService.search(query, limit);
		List<Member> found = new ArrayList<Member>();
		for (ScoredDocument scoredDoc : results) {
			found.add(docToMember(scoredDoc));
		}
		return found;
	}

	private Member docToMember(ScoredDocument doc) {
		Member member = new Member();
		member.setGplusId(doc.getOnlyField(memberMeta.gplusId.getAttributeName()).getText());
		member.setMail(doc.getOnlyField(memberMeta.mail.getAttributeName()).getText());
		member.setTwitterId(doc.getOnlyField(memberMeta.twitterId.getAttributeName()).getText());
		member.setApproved(Boolean.parseBoolean(doc.getOnlyField(memberMeta.approved.getAttributeName()).getText()));
		return member;
	}

	public ProcessResult importCsv(FileItem fileItem) {
		try {
			InputStream inputStream = new ByteArrayInputStream(fileItem.getData());
			String contentType = fileItem.getContentType();
			if (contentType == null) {
				contentType = "";
			}

			if (contentType.contains("text") && fileItem.getFileName().endsWith(".csv")) {
				// テキストファイルの場合またはファイル名の拡張子が".csv"の場合
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

				// 入力
				CsvConfig cfg = new CsvConfig();
				cfg.setQuoteDisabled(false); // デフォルトでは無効となっている囲み文字を有効にします。
				cfg.setEscapeDisabled(false); // デフォルトでは無効となっているエスケープ文字を有効にします。
				cfg.setBreakString("\n"); // 項目値中の改行を \n で置換えます。
				// cfg.setNullString("NULL"); // null 値扱いする文字列を指定します。
				cfg.setIgnoreLeadingWhitespaces(true); // 項目値前のホワイトスペースを除去します。
				cfg.setIgnoreTrailingWhitespaces(true); // 項目値後のホワイトスペースを除去します。
				cfg.setIgnoreEmptyLines(true); // 空行を無視するようにします。
				// cfg.setIgnoreLinePatterns(Pattern.compile("^#.*$")); //
				// 正規表現による無視する行パターンを設定します。(この例では # で始まる行)
				// cfg.setSkipLines(1); // 最初の1行目をスキップして読込みます。

				List<Map<String, String>> memberList = Csv.load(bufferedReader, cfg, new ColumnNameMapListHandler());

				for (Map member : memberList) {
					this.addMember(member);
				}

				String message = memberList.size() + "件登録しました。";
				return new ProcessResult(ProcessStatus.SUCCESS, message, memberList.size());

			} else {
				return new ProcessResult(ProcessStatus.ERROR, "アップロードされたファイルがCSVではありません。", 0);
			}
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			return new ProcessResult(ProcessStatus.ERROR, "エラーが発生しました。", 0);
		}
	}

}
