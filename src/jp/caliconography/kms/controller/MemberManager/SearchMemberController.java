package jp.caliconography.kms.controller.MemberManager;

import java.util.List;
import java.util.logging.Logger;

import jp.caliconography.kms.meta.MemberMeta;
import jp.caliconography.kms.model.Member;
import jp.caliconography.kms.service.MemberManagerService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

public class SearchMemberController extends Controller {

	private MemberManagerService service = new MemberManagerService();

	private static final Logger LOG = Logger.getLogger(SearchMemberController.class.getName());

	@Override
	public Navigation run() throws Exception {
		RequestMap requestMap = new RequestMap(request);
		String limitStr = (String) (requestMap.get("limit") == null ? "" : requestMap.get("limit"));
		int limit = 10;
		if (limitStr != null && limitStr != "") {
			try {
				limit = Integer.parseInt(limitStr);
			} catch (NumberFormatException e) {
				LOG.severe("Failed to parse " + limitStr);
			}
		}
		List<Member> memberList = service.searchMember((String) (requestMap.get("query") == null ? "" : requestMap.get("query")), limit);
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.getWriter().println(requestMap.get("callback") + "(" + MemberMeta.get().modelsToJson(memberList.toArray()) + ");");
		response.flushBuffer();

		return null;
	}
}
