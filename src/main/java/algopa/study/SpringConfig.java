package algopa.study;

import algopa.study.member.JpaMemberRepository;
import algopa.study.member.MemberRepository;
import algopa.study.member.MemberService;
import algopa.study.member.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em;

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    public MemberRepository memberRepository() {
        return new JpaMemberRepository(em);
    }
}
