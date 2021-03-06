package jp.caliconography.kms.controller.MemberManager;

import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class GetMemberControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.start("/MemberManager/GetMember");
        GetLoginMemberController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is("/MemberManager/GetMember.jsp"));
    }
}
