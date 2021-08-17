package algopa.study.service;

import algopa.study.domain.Member;

import java.util.List;

public interface MemberService {
    Long join(Member member);
    Long edit(Long id, Member member);
    void deleteMember(Long id);
    Boolean checkDuplicateMember(Member member);
    List<Member> findAllMembers();
}
