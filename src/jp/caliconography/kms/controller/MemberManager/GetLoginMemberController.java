package jp.caliconography.kms.controller.MemberManager;

import jp.caliconography.kms.meta.MemberMeta;
import jp.caliconography.kms.model.Member;
import jp.caliconography.kms.service.MemberManagerService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class GetLoginMemberController extends Controller {

	private MemberManagerService service = new MemberManagerService();

    @Override
    public Navigation run() throws Exception {

		RequestMap requestMap = new RequestMap(request);
		UserService userService = UserServiceFactory.getUserService();
		Member member = service.getMember(userService.getCurrentUser().getEmail());
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		response.getWriter().println(requestMap.get("callback") + "(" + MemberMeta.get().modelToJson(member) + ");");
		response.flushBuffer();

		return null;
    }
}
