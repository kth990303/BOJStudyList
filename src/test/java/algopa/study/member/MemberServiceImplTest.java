package algopa.study.member;

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
        Member member=new Member("test", "Silver V");
        Member member2=new Member("test", "Silver IV");
        // 현재 테스트 대상은 티어만 바꾸는 member1, member2만
        Member member3=new Member("test2", "Silver V");
        Member member4=new Member("test2", "Silver IV");
        repository.save(member);
        Optional<Member> findMemberOptional = repository.findByName(member.getName());
        Member findMember = findMemberOptional.get();

        service.edit(findMember.getId(), member2);

        Assertions.assertThat(findMember.getTier()).isEqualTo("Silver IV");
    }

    @Test
    void join() {
        Member member=new Member("test", "Silver V");

        service.join(member);
        Optional<Member> findMemberOptional = repository.findByName(member.getName());
        Member findMember = findMemberOptional.get();

        Assertions.assertThat(findMember).isEqualTo(member);
    }

    @Test
    void deleteMember() {
        Member member=new Member("test", "Silver V");

        repository.save(member);
        List<Member> memberList = repository.findAll();
        service.deleteMember(member.getId());
        List<Member> members = repository.findAll();

        Assertions.assertThat(members.size()).isEqualTo(memberList.size()-1);
    }

    @Test
    void checkDuplicateMember() {
        Member member=new Member("test", "Silver V");
        Member member2=new Member("test", "Silver V");
        Member member3=new Member("test", "Platinum II");

        repository.save(member);
        Boolean canMember2 = service.checkDuplicateMember(member2);
        Boolean canMember3 = service.checkDuplicateMember(member3);

        Assertions.assertThat(!canMember2 && !canMember3).isEqualTo(true);
    }

    @Test
    void findAllMembers() {
        Member member=new Member("test", "Silver V");
        Member member2=new Member("test", "Silver IV");
        Member member3=new Member("test2", "Silver V");
        Member member4=new Member("test2", "Silver IV");

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