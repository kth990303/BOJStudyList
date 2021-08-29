package algopa.study.member;

import java.util.List;

public interface MemberService {
    Long join(Member member);
    Long edit(Long id, Member changeMember);
    void deleteMember(Long id);
    Boolean checkDuplicateMember(Member member);
    List<Member> findAllMembers();
    // 로그인 관련
    void saltUserPassword(Member member);
    Member loginMember(String username, String password);
}
