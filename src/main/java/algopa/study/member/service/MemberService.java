package algopa.study.member.service;


import algopa.study.member.domain.Member;
import algopa.study.member.mapper.MemberIdMapper;
import algopa.study.member.repository.MemberRepository;
import algopa.study.member.domain.MemberRole;
import algopa.study.member.dto.MemberDto;
import algopa.study.member.dto.MemberIdDto;
import algopa.study.member.mapper.MemberMapper;
import algopa.study.post.domain.Post;
import algopa.study.post.dto.PostIdDto;
import algopa.study.post.mapper.PostIdMapper;
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
    private final MemberIdMapper idMapper=Mappers.getMapper(MemberIdMapper.class);

    // 스프링 시큐리티 ROLE_ADMIN, ROLE_USER 부여 메소드
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Member findMember = memberRepository.findByName(name);
        List<GrantedAuthority> roles=new ArrayList<>();
        if(findMember.getName().equals("kth990303")){
            roles.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        }
        else if(findMember.getIsMember()){
            roles.add(new SimpleGrantedAuthority(MemberRole.GUEST.getValue()));
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
        // 관리자가 아닌, 자기 자신의 정보수정일 경우 비밀번호 변경 가능
        else{
            member.updateMember(memberDto.getTier(), memberDto.getEmail());
        }
        // 관리자가 비밀번호를 변경할 수 없도록 하는 경우는 비밀번호에 null이 들어가므로
        // 아예 변경이 되지 않음.
        // 관리자도 변경이 불가능하도록 하려면 아래 mapper 코드를 이용할 것.
        //mapper.updateFromDto(memberDto, member);
        return member.getId();
    }

    //회원 가입
    public Long join(MemberDto memberDto) {
        if (checkDuplicateMember(memberDto)) {
            return -1L;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        memberDto.setPassword(encoder.encode(memberDto.getPassword()));
        return memberRepository.save(toEntity(memberDto)).getId();
    }
    public void changePreMemberToMember(Long id){
        Optional<Member> member = memberRepository.findById(id);
        if(member.isEmpty()){
            throw new NoSuchElementException("회원이 존재하지 않습니다.");
        }
        member.get().updateMember(true);
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
    // Id까지 필요하므로 MemberIdDto를 출력.
    // 클라이언트는 id는 필요없으므로 id를 담는 dto와 담지 않는 dto로 분리함.
    @Transactional(readOnly = true)
    public List<MemberIdDto> findAllMembers(){
        List<Member> members = (List)memberRepository.findAllByIsMemberTrue();
        return idMapper.toDtoList(members);
    }
    @Transactional(readOnly = true)
    public List<MemberIdDto> findAllPreMembers(){
        List<Member> members = (List)memberRepository.findAllByIsMemberFalse();
        return idMapper.toDtoList(members);
    }

    // MapStruct Mapper Entity <-> MemberDto
    protected MemberDto toDto(Member member){
        return mapper.toDto(member);
    }
    protected Member toEntity(MemberDto memberDto){
        return mapper.toEntity(memberDto);
    }

    // MapStruct Mapper Entity <-> MemberIdDto
    protected MemberIdDto toIdDto(Member member){
        return idMapper.toDto(member);
    }
    protected Member toEntity(MemberIdDto memberIdDto){
        return idMapper.toEntity(memberIdDto);
    }
}
