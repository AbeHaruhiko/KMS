package jp.caliconography.kms.controller.MemberManager;

import org.slim3.controller.Controller;
import org.slim3.controller.Navigation;

/***
 * MemberManager/へのアクセス時のController
 * index.htmlがある場合は呼ばれない。
 * Controllerが呼ばれない場合は、web.xmlのsecurity-constraintの対象外になるため、
 * ログイン無しに表示されるが、index.html中のAjax処理はログインが必要なため、
 * エラーが発生し中途半端なHTMLが表示される。
 * そこで、jspとして扱うことでControllerが呼ばれる＝security-constraintの対象とする。
 * 中身はhtmlそのもの。
 * @author abe
 *
 */
public class IndexController extends Controller {
    @Override
    public Navigation run() throws Exception {
        return forward("index.jsp");
    }
}
