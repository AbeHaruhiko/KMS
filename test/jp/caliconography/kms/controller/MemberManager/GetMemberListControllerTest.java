package jp.caliconography.kms.controller.MemberManager;

import java.util.List;

import jp.caliconography.kms.model.Member;

import org.slim3.datastore.Datastore;
import org.slim3.tester.ControllerTestCase;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class GetMemberListControllerTest extends ControllerTestCase {

    @Test
    public void run() throws Exception {
        Member member = new Member();
        member.setGplusId("104106466325561657935");
        Datastore.put(member);
        member = new Member();
        member.setGplusId("2");
        Datastore.put(member);
        member = new Member();
        member.setGplusId("3");
        Datastore.put(member);

        tester.param("callback", "JSON_CALLBACK");
        tester.start("/MemberManager/GetMemberList");
        GetMemberListController controller = tester.getController();
        assertThat(controller, is(notNullValue()));
        assertThat(tester.isRedirect(), is(false));
        assertThat(tester.getDestinationPath(), is(nullValue()));
        
//        List<Member> storedMemberList = Datastore.query(Member.class).asList();
//        assertThat(storedMemberList, is(notNullValue()));
//        assertThat(storedMemberList.size(), is(3));
//        assertThat(storedMemberList.get(0).getGplusId(), is(notNullValue(String.class)));

    }
}
