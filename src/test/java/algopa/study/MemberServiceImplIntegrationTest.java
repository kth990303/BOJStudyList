package algopa.study;

import algopa.study.domain.Member;
import algopa.study.repository.MemberRepository;
import algopa.study.service.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberServiceImplIntegrationTest {
    @Autowired
    MemberServiceImpl memberServiceImpl;
    @Autowired
    MemberRepository memberRepository;
    @Test
    void 회원가입() {
        // given
        Member member=new Member();
        member.setName("kth990303");
        member.setTier("Platinum II");
        // when
        Long saveId= memberServiceImpl.join(member);
        // then
        assertThat(member.getName()).isEqualTo("kth990303");
    }
}
