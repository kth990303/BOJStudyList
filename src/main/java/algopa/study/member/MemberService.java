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

    // 스프링 시큐리티 ROLE_ADMIN, ROLE_USER 부여 메소드
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
    // 회원 정보수정
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

        // 관리자는 비밀번호는 변경할 수 없도록
        if (authMemberName.equals(memberDto.getName())) {
            memberDto.setPassword(encoder.encode(memberDto.getPassword()));
            member.updateMember(memberDto.getTier(), memberDto.getEmail(), memberDto.getPassword());
        }
        member.updateMember(memberDto.getTier(), memberDto.getEmail());
        // 관리자가 비밀번호를 변경할 수 없도록 하는 경우는 비밀번호에 null이 들어가므로
        // 아예 변경이 되지 않음.
        // 관리자도 변경이 불가능하도록 하려면 아래 mapper 코드를 이용할 것.
        //mapper.updateFromDto(memberDto, member);
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
    // 회원 DTO 찾기 (id, name)
    @Transactional(readOnly = true)
    public MemberDto findById(Long id){
        Optional<Member> member = memberRepository.findById(id);
        if(member.isEmpty()){
            throw new NoSuchElementException("회원이 존재하지 않습니다.");
        }
        return toDto(member.get());
    }
    @Transactional(readOnly = true)
    public MemberDto findByName(String name){
        return toDto(memberRepository.findByName(name));
    }
    @Transactional(readOnly = true)
    public Long findIdByName(String name){
        return memberRepository.findByName(name).getId();
    }
    // 회원 엔티티 찾기
    // 엔티티를 필요로 하는 매핑에만 최소한으로 사용할 것
    @Transactional(readOnly = true)
    public Member findEntityById(Long id){
        return memberRepository.findById(id).get();
    }
    // 엔티티를 필요로 하는 매핑에만 최소한으로 사용할 것
    @Transactional(readOnly = true)
    public Member findEntityByName(String name){
        return memberRepository.findByName(name);
    }
    //회원 삭제
    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }
    //중복 회원 존재여부 확인
    @Transactional(readOnly = true)
    public Boolean checkDuplicateMember(MemberDto memberDto) {
        return memberRepository.existsByName(toEntity(memberDto).getName());
    }
    // 전체 회원 조회
    // index 홈페이지에서 회원번호 출력을 해야 하고, @pathvariable 요구되는 다른 매핑도 존재하므로
    // Entity를 출력하되, readOnly로 대체.
    @Transactional(readOnly = true)
    public List<Member> findAllMembers(){
        return (List)memberRepository.findAll();
    }

    // MapStruct Mapper Entity <-> Dto
    protected MemberDto toDto(Member member){
        return mapper.toDto(member);
    }
    protected Member toEntity(MemberDto memberDto){
        return mapper.toEntity(memberDto);
    }
}
