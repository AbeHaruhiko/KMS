package jp.caliconography.kms.controller.Join;

import jp.caliconography.kms.meta.ProcessResultMeta;
import jp.caliconography.kms.model.ProcessResult;
import jp.caliconography.kms.service.MemberManagerService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

public class ApplyController extends Controller {

    private MemberManagerService service = new MemberManagerService();
    @Override
    public Navigation run() throws Exception {
        ProcessResult result = service.apply(new RequestMap(request));


		response.setCharacterEncoding("UTF-8");
		response.getWriter().println(ProcessResultMeta.get().modelToJson(result));
	    response.flushBuffer();

	    return null;
    }
}
