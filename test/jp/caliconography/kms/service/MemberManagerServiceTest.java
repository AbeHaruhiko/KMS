package jp.caliconography.kms.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.caliconography.kms.model.Member;

import org.junit.Test;
import org.slim3.datastore.Datastore;
import org.slim3.tester.AppEngineTestCase;

public class MemberManagerServiceTest extends AppEngineTestCase {

    private MemberManagerService service = new MemberManagerService();

    @Test
    public void test() throws Exception {
        assertThat(service, is(notNullValue()));
    }
    
    @Test
    public void addMember() throws Exception {
        Map<String, Object> input = new HashMap<String, Object>();
        input.put("gplusId", "104106466325561657935");
        Member added = service.addMember(input);
        assertThat(added, is(notNullValue()));
        Member stored = Datastore.get(Member.class, added.getKey());
        assertThat(stored.getGplusId(), is("104106466325561657935"));
    }
    
    @Test
    public void getMemberList() throws Exception {
//        Transaction tx = Datastore.beginTransaction();
//        DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
//        Transaction tx = datastoreService.beginTransaction(withXG(false));
        
        Member member = new Member();
        member.setGplusId("104106466325561657935");
        Datastore.put(member);
        member = new Member();
        member.setGplusId("2");
        Datastore.put(member);
        member = new Member();
        member.setGplusId("3");
        Datastore.put(member);
        List<Member> memberList = service.getMemberList();
        assertThat(memberList.size(), is(3));
        assertThat(memberList.get(0).getGplusId(), is("104106466325561657935"));
        assertThat(memberList.get(1).getGplusId(), is("2"));
        assertThat(memberList.get(2).getGplusId(), is("3"));
    }
}


