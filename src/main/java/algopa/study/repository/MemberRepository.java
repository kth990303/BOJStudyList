package algopa.study.repository;

import algopa.study.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    void deleteById(Long id);
    Optional<Member> findByName(String name);
    Optional<Member> findById(Long id);
    Member edit(Long id, String tier);
    List<Member> findAll();
}
