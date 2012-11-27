package jp.caliconography.kms.controller.MemberManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jp.caliconography.kms.model.Member;
import jp.caliconography.kms.service.MemberManagerService;
import jp.sf.orangesignal.csv.CsvConfig;
import jp.sf.orangesignal.csv.CsvWriter;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

public class DownloadCsvController extends Controller {

    private MemberManagerService service = new MemberManagerService();

    @Override
    public Navigation run() throws Exception {
    	
        List<Member> memberList = service.getMemberList();
        
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "filename=\"satonotami" + this.getDateString(new Date()) + "\"");

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
		cfg.setLineSeparator(System.getProperty("line.separator"));

        CsvWriter csvWriter = new CsvWriter(response.getWriter(), cfg);

        for (Member member: memberList) {
            csvWriter.writeValues(member.getAttributeList());
        }
        csvWriter.flush();
        response.flushBuffer();


        return null;
    }
    
    private String getDateString(Date d) {
    	  SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
    	  return f.format(d);
    } 
}
