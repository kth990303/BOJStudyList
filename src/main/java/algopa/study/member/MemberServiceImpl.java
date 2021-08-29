package algopa.study.member;

import algopa.study.salt.Salt;
import algopa.study.salt.SaltRepository;
import algopa.study.salt.SaltUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@Getter @Setter
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    private final SaltRepository saltRepository;

    private final SaltUtil saltUtil;

    @Override
    public void saltUserPassword(Member member) {
        String password=member.getPassword();
        String salt = saltUtil.genSalt();
        log.info("salt={}", salt);
        member.setSalt(new Salt(salt));
        member.setPassword(saltUtil.encodePassword(salt, password));
    }

    @Override
    public Member loginMember(String username, String password) {
        Member findMember = memberRepository.findByName(username);
        if(findMember==null){
            return null;
        }
        String salt=findMember.getSalt().getSalt();
        String encodePassword = saltUtil.encodePassword(salt, password);
        if(findMember.getPassword().equals(encodePassword))
            return findMember;
        else
            return null;
    }

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
        saltUserPassword(member);
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
