package jp.caliconography.kms.controller.MemberManager;

import jp.caliconography.kms.meta.ProcessResultMeta;
import jp.caliconography.kms.model.ProcessResult;
import jp.caliconography.kms.service.MemberManagerService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

public class DeleteMemberController extends Controller {

    private MemberManagerService service = new MemberManagerService();

    @Override
    public Navigation run() throws Exception {
		RequestMap requestMap = new RequestMap(request);

		ProcessResult result = service.approve(requestMap);

		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(requestMap.get("callback") + "(" + ProcessResultMeta.get().modelToJson(result) + ");");
	    response.flushBuffer();

	    return null;
    }
}
