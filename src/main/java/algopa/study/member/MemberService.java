package algopa.study.member;

import algopa.study.salt.Salt;
import algopa.study.salt.SaltRepository;
import algopa.study.salt.SaltUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@Transactional
@Getter @Setter
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Member findMember = memberRepository.findByName(name);
        List<GrantedAuthority> roles=new ArrayList<>();
        if(findMember.getName().equals("kth990303")){
            roles.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        }
        else{
            roles.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
        }
        return new User(findMember.getName(), findMember.getPassword(), roles);
    }


    public Long edit(Long id, Member changeMember) {
        Optional<Member> findMember = memberRepository.findById(id);
        if(findMember.isPresent()){
            BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

            Member member = findMember.get();
            member.setEmail(changeMember.getEmail());
            member.setTier(changeMember.getTier());
            member.setPassword(encoder.encode(changeMember.getPassword()));
            memberRepository.save(member);
        }
        return id;
    }

    //회원 가입
    public Long join(Member member){
        if(checkDuplicateMember(member)){
            return -1L;
        }
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        member.setPassword(encoder.encode(member.getPassword()));
        memberRepository.save(member);
        return member.getId();
    }
    public Member findById(Long id){
        Optional<Member> member = memberRepository.findById(id);
        return member.get();
    }
    public Member findByName(String name){
        return memberRepository.findByName(name);
    }
    //회원 삭제
    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }
    //중복 회원 존재여부 확인
    public Boolean checkDuplicateMember(Member member) {
        return memberRepository.existsByName(member.getName());
    }
    //전체 회원 조회
    public List<Member> findAllMembers(){
       return (List)memberRepository.findAll();
    }


}
