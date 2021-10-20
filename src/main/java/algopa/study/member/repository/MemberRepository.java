package algopa.study.member.repository;

import algopa.study.member.domain.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface MemberRepository extends CrudRepository<Member, Long> {
    Member findByName(String name);
    Boolean existsByName(String name);
    List<Member> findAllByIsMemberTrue();
    // 회원가입 대기자
    List<Member> findAllByIsMemberFalse();
}
