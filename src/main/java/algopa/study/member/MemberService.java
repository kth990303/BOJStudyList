package algopa.study.member;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final MemberMapper mapper= Mappers.getMapper(MemberMapper.class);

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Member findMember = memberRepository.findByName(name);
        List<GrantedAuthority> roles=new ArrayList<>();
        if(findMember.getName().equals("kth990303")){
            roles.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        }
        roles.add(new SimpleGrantedAuthority(MemberRole.USER.getValue()));
        return new User(findMember.getName(), findMember.getPassword(), roles);
    }


    public Long edit(Long id, MemberDto memberDto) {
        Optional<Member> findMember = memberRepository.findById(id);
        log.info("findMember={}", findMember.get().getName());
        if(findMember.isEmpty()){
            log.info("회원이 존재하지 않습니다.");
            return -1L;
        }
        Member member = findMember.get();
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

        String authMemberName = SecurityContextHolder.getContext().getAuthentication().getName();

        if (authMemberName.equals(memberDto.getName())) {
            memberDto.setPassword(encoder.encode(memberDto.getPassword()));
            member.updateMember(memberDto.getTier(), memberDto.getEmail(), memberDto.getPassword());
        }
        member.updateMember(memberDto.getTier(), memberDto.getEmail());
        return member.getId();
    }

    //회원 가입
    public Long join(MemberDto memberDto){
        if(checkDuplicateMember(memberDto)){
            return -1L;
        }
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        memberDto.setPassword(encoder.encode(memberDto.getPassword()));
        return memberRepository.save(toEntity(memberDto)).getId();
    }
    public MemberDto findById(Long id){
        Optional<Member> member = memberRepository.findById(id);
        if(member.isEmpty()){
            throw new NoSuchElementException("회원이 존재하지 않습니다.");
        }
        return toDto(member.get());
    }
    public MemberDto findByName(String name){
        return toDto(memberRepository.findByName(name));
    }
    //회원 삭제
    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }
    //중복 회원 존재여부 확인
    public Boolean checkDuplicateMember(MemberDto memberDto) {
        return memberRepository.existsByName(toEntity(memberDto).getName());
    }
    //전체 회원 조회
    public List<MemberDto> findAllMembers(){
        Iterable<Member> all = memberRepository.findAll();
        for (Member member : all) {
            log.info("id={}, dtoId={}", member.getId(), toDto(member).getId());
        }
        return mapper.toDtoList((List<Member>) memberRepository.findAll());
    }

    // MapStruct Mapper Entity <-> Dto
    protected MemberDto toDto(Member member){
        return mapper.toDto(member);
    }
    protected Member toEntity(MemberDto memberDto){
        return mapper.toEntity(memberDto);
    }
}
