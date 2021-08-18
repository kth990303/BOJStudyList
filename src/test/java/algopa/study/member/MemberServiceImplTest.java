package algopa.study.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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
    }

    @Test
    @Transactional
    @Rollback
    void join() {
        Member member=new Member("test", "Silver V");

        repository.save(member);
        Optional<Member> findMemberOptional = repository.findByName(member.getName());
        Member findMember = findMemberOptional.get();

        Assertions.assertThat(findMember).isEqualTo(member);
    }

    @Test
    void deleteMember() {
    }

    @Test
    void checkDuplicateMember() {
    }

    @Test
    void findAllMembers() {
    }
}