package algopa.study.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Getter @Setter
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    @Override
    public Long edit(Long id, Member changeMember) {
        Optional<Member> findMember = memberRepository.findById(id);
        if(findMember.isPresent()){
            Member member = findMember.get();
            member.setEmail(changeMember.getEmail());
            member.setTier(changeMember.getTier());
            member.setPassword(changeMember.getPassword());
            memberRepository.save(member);
        }
        return id;
    }

    //회원 가입
    @Override
    public Long join(Member member){
        if(checkDuplicateMember(member)){
            return -1L;
        }
        memberRepository.save(member);
        return member.getId();
    }
    //회원 삭제
    @Override
    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }
    //중복 회원 존재여부 확인
    @Override
    public Boolean checkDuplicateMember(Member member) {
        return memberRepository.existsByName(member.getName());
    }
    //전체 회원 조회
    @Override
    public List<Member> findAllMembers(){
       return (List)memberRepository.findAll();

    }
}
