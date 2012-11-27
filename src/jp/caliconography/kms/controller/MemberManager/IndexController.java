package jp.caliconography.kms.controller.MemberManager;

import java.util.List;

import jp.caliconography.kms.model.Member;
import jp.caliconography.kms.service.MemberManagerService;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

/***
 * MemberManager/へのアクセス時のController
 * index.htmlがある場合は呼ばれないので、このControllerは呼ばれない。
 * @author abe
 *
 */
public class IndexController extends Controller {

    private MemberManagerService service =new MemberManagerService();
    
    @Override
    public Navigation run() throws Exception {
        List<Member> memberList = service.getMemberList();
        requestScope("memberList", memberList);
        return forward("index.jsp");
    }
}
