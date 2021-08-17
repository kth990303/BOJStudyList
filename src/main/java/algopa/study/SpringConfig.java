package algopa.study;

import algopa.study.repository.JpaMemberRepository;
import algopa.study.repository.MemberRepository;
import algopa.study.service.MemberService;
import algopa.study.service.MemberServiceImpl;
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
