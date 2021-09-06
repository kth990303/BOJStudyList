package algopa.study.member;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback
@Slf4j
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
        MemberDto member=new MemberDto("test", "Silver V", "123456", "test@naver.com");
        MemberDto member2=new MemberDto("test", "Silver IV", "12345", "test@gmail.com");

        Long joinMemberId = service.join(member);
        // security test 공부하기
        // 현재, 권한이 없어서 edit이 작동이 안돼서 test fail
        service.edit(joinMemberId, member2);

        Assertions.assertThat(member.getTier()).isEqualTo("Silver IV");
        Assertions.assertThat(member.getEmail()).isEqualTo("test@gmail.com");
    }

    @Test
    void join() {
        MemberDto member=new MemberDto("test", "Silver V", "test@naver.com", "123456");
        log.info("member.email={}", member.getEmail());
        Long joinMemberId = service.join(member);
        Member findMember = repository.findByName("test");
        log.info("findMember.email={}", findMember.getEmail());
        Assertions.assertThat(findMember.getId()).isEqualTo(joinMemberId);
    }

    @Test
    void deleteMember() {
        Member member=new Member("test", "Silver V", "test@naver.com", "12345");

        repository.save(member);
        List<Member> memberList = service.findAllMembers();
        service.deleteMember(member.getId());
        List<Member> members = service.findAllMembers();

        Assertions.assertThat(members.size()).isEqualTo(memberList.size()-1);
        Assertions.assertThat(members).doesNotContain(member);
    }

    @Test
    void checkDuplicateMember() {
        MemberDto member=new MemberDto("test", "Silver V", "test@naver.com", "12345");
        MemberDto member2=new MemberDto("test", "Silver V", "test2@naver.com", "12345");
        MemberDto member3=new MemberDto("test", "Platinum II", "test3@naver.com", "1234");

        service.join(member);
        Boolean canMember2 = service.checkDuplicateMember(member2);
        Boolean canMember3 = service.checkDuplicateMember(member3);

        Assertions.assertThat(canMember2).isEqualTo(true);
        Assertions.assertThat(canMember3).isEqualTo(true);
    }

    @Test
    void findAllMembers() {
        MemberDto member1=new MemberDto("test", "Silver V", "test@naver.com", "123");
        MemberDto member2=new MemberDto("test2", "Silver IV", "test2@naver.com", "12");
        MemberDto member3=new MemberDto("test3", "Silver V", "test3@naver.com", "!234");

        Long member1Id = service.join(member1);
        Long member2Id = service.join(member2);
        Long member3Id = service.join(member3);

        // findall 테스트코드 다시 짜기
    }
}