package jp.caliconography.kms.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import jp.caliconography.kms.meta.MemberMeta;
import jp.caliconography.kms.model.Member;
import jp.caliconography.kms.model.ProcessResult;
import jp.caliconography.kms.model.ProcessResult.ProcessStatus;
import jp.sf.orangesignal.csv.Csv;
import jp.sf.orangesignal.csv.CsvConfig;
import jp.sf.orangesignal.csv.handlers.ColumnNameMapListHandler;

import org.slim3.controller.upload.FileItem;
import org.slim3.datastore.Datastore;
import org.slim3.util.BeanUtil;

import com.google.appengine.api.datastore.Transaction;


public class MemberManagerService {

    private MemberMeta memberMeta = new MemberMeta();

    public Member addMember(Map<String, Object> input) {
        Member member = new Member();
        BeanUtil.copy(input, member);
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(member);
        tx.commit();
        return member;
    }

    public ProcessResult apply(Map<String, Object> input) {
        Member member = new Member();
        BeanUtil.copy(input, member);
        member.setApproved(false);
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(member);
        tx.commit();
		return new ProcessResult(ProcessStatus.SUCCESS, "入里申請しました！G+での連絡をお待ちください。<br/>数日以内に連絡がない場合は、G+でハッシュタグ<a href='https://plus.google.com/u/0/s/%23%E9%9A%A0%E6%AD%A6%E5%A3%AB%E4%B9%83%E9%87%8C%E5%85%A5%E9%87%8C%E7%94%B3%E8%AB%8B' target='_blank'>#隠武士の里入里申請</a>を付けてご連絡ください。", 1);
    }

    public ProcessResult approve(Map<String, Object> input) {
        Member member = new Member();
        BeanUtil.copy(input, member);
        Member storedMember = Datastore.query(memberMeta).filter(memberMeta.gplusId.equal(member.getGplusId())).asSingle();
        if (member.isApproved()) {
            storedMember.setApproved(false);
        } else {
            storedMember.setApproved(true);
        }
        Transaction tx = Datastore.beginTransaction();
        Datastore.put(storedMember);
        tx.commit();
		return new ProcessResult(ProcessStatus.SUCCESS, storedMember.isApproved() ? "承認しました！" : "承認を取り消しました！", 1);
    }

    public List<Member> getMemberList() {
        return Datastore.query(memberMeta).sort(memberMeta.gplusId.asc).asList();
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

				String message =memberList.size() + "件登録しました。";
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
