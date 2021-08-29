package algopa.study.member;

import algopa.study.salt.SaltRepository;
import algopa.study.salt.SaltUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class MemberServiceImplTest {
    @Autowired
    MemberService service;
    @Autowired
    MemberRepository repository;

    @BeforeEach
    public void beforeEach(){
    }

    @AfterEach
    public void afterEach(){
    }

    @Test
    void edit() {
        Member member=new Member("test", "Silver V", "test@naver.com");
        Member member2=new Member("test", "Silver IV", "test@gmail.com");

        repository.save(member);
        Member findMember = repository.findByName(member.getName());

        service.edit(findMember.getId(), member2);

        Assertions.assertThat(member.getTier()).isEqualTo("Silver IV");
        Assertions.assertThat(member.getEmail()).isEqualTo("test@gmail.com");
    }

    @Test
    void join() {
        Member member=new Member("test", "Silver V", "test@naver.com", "123456");

        service.join(member);
        Member findMember = repository.findByName("test");

        Assertions.assertThat(findMember).isEqualTo(member);
    }

    @Test
    void login(){
        Member member=new Member("test2", "Silver V", "test@naver.com", "123456");
        service.join(member);

        String name="test2";
        String password="123456";

        Member loginMember = service.loginMember(name, password);
        Assertions.assertThat(member).isEqualTo(loginMember);
    }

    @Test
    void deleteMember() {
        Member member=new Member("test", "Silver V", "test@naver.com");

        repository.save(member);
        List<Member> memberList = service.findAllMembers();
        service.deleteMember(member.getId());
        List<Member> members = service.findAllMembers();

        Assertions.assertThat(members.size()).isEqualTo(memberList.size()-1);
        Assertions.assertThat(members).doesNotContain(member);
    }

    @Test
    void checkDuplicateMember() {
        Member member=new Member("test", "Silver V", "test@naver.com");
        Member member2=new Member("test", "Silver V", "test2@naver.com");
        Member member3=new Member("test", "Platinum II", "test3@naver.com");

        repository.save(member);
        Boolean canMember2 = service.checkDuplicateMember(member2);
        Boolean canMember3 = service.checkDuplicateMember(member3);

        Assertions.assertThat(canMember2).isEqualTo(true);
        Assertions.assertThat(canMember3).isEqualTo(true);
    }

    @Test
    void findAllMembers() {
        Member member=new Member("test", "Silver V", "test@naver.com");
        Member member2=new Member("test2", "Silver IV", "test2@naver.com");
        Member member3=new Member("test3", "Silver V", "test3@naver.com");
        Member member4=new Member("test4", "Silver IV", "test4@gmail.com");

        repository.save(member);
        repository.save(member2);
        repository.save(member3);
        repository.save(member4);
        List<Member> allMembers = service.findAllMembers();

        Assertions.assertThat(allMembers).contains(member);
        Assertions.assertThat(allMembers).contains(member2);
        Assertions.assertThat(allMembers).contains(member3);
        Assertions.assertThat(allMembers).contains(member4);
    }
}