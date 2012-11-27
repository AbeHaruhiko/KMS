package jp.caliconography.kms.controller.MemberManager;

import java.util.List;

import jp.caliconography.kms.meta.MemberMeta;
import jp.caliconography.kms.model.Member;
import jp.caliconography.kms.service.MemberManagerService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class GetMemberListController extends Controller {

	private MemberManagerService service = new MemberManagerService();

	@Override
	public Navigation run() throws Exception {

		UserService userService = UserServiceFactory.getUserService();
		String thisURL = request.getRequestURI();
		if (request.getUserPrincipal() != null) {
			// ログインしている場合の処理
//			requestScope("loginInfo", "<p>You can <a href=\"" + userService.createLogoutURL(thisURL) + "\">sign out</a>.</p>");
			String userId = userService.getCurrentUser().getUserId();
			this.servletContext.log(userId);
			// 他の処理
		} else {
			// ログインしていない場合の処理
//			requestScope("loginInfo", "<p>Please <a href=\"" + userService.createLoginURL(thisURL) + "\">sign in</a>.</p>");
		}
		
		
		
		RequestMap requestMap = new RequestMap(request);
		List<Member> memberList = service.getMemberList();
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.getWriter().println(requestMap.get("callback") + "(" + MemberMeta.get().modelsToJson(memberList.toArray()) + ");");
		response.flushBuffer();

		return null;
	}
}
