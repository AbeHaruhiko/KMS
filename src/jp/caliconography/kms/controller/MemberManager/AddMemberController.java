package jp.caliconography.kms.controller.MemberManager;

import jp.caliconography.kms.service.MemberManagerService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;
import org.slim3.util.RequestMap;

public class AddMemberController extends Controller {

    private MemberManagerService service = new MemberManagerService();
    @Override
    public Navigation run() throws Exception {
        service.addMember(new RequestMap(request));
        return redirect(basePath);
    }
}
