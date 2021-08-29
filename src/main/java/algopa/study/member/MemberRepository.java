package algopa.study.member;

import org.springframework.data.repository.CrudRepository;


public interface MemberRepository extends CrudRepository<Member, Long> {
    Member findByName(String name);
    Boolean existsByName(String name);
}
