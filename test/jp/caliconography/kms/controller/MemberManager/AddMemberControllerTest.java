package jp.caliconography.kms.controller.MemberManager;

import jp.caliconography.kms.model.Member;

import org.slim3.datastore.Datastore;
import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AddMemberControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        tester.param("gplusId", "104106466325561657935");
        tester.start("/MemberManager/addMember");
        AddMemberController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(true));
        assertThat(tester.getDestinationPath(), is("/MemberManager/"));
        
        Member stored = Datastore.query(Member.class).asSingle();
        assertThat(stored, is(notNullValue()));
        assertThat(stored.getGplusId(), is("104106466325561657935"));
    }
}
