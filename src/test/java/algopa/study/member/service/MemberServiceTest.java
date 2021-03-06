package algopa.study.member.service;

import algopa.study.member.domain.Member;
import algopa.study.member.dto.MemberDto;
import algopa.study.member.dto.MemberIdDto;
import algopa.study.member.mapper.MemberIdMapper;
import algopa.study.member.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
@Slf4j
class MemberServiceTest {
    @Autowired
    MemberService service;
    @Autowired
    MemberIdMapper memberIdMapper;

    @BeforeEach
    public void beforeEach(){
    }

    @AfterEach
    public void afterEach(){
    }

    private void createMember() {
        Member member=new Member("test", "Bronze V", "test@naver.com", "123456");
        Member member2=new Member("test2", "Bronze V", "test2@naver.com", "1234");
        Member member3=new Member("test3", "Platinum V", "test@naver.com", "123456");

        service.join(service.toDto(member));
        service.join(service.toDto(member2));
        service.join(service.toDto(member3));
    }

    @Test
    void 회원가입() {
        createMember();

        MemberDto findMemberDto = service.findByName("test");
        Assertions.assertThat(findMemberDto.getEmail()).isEqualTo("test@naver.com");
        Assertions.assertThat(findMemberDto.getTier()).isEqualTo("Bronze V");
    }

    @Test
    void 회원삭제() {
        createMember();

        MemberDto findMemberDto = service.findByName("test");
        Long findMemberId = service.findIdByName("test");
        service.deleteMember(findMemberId);

        MemberDto findDto = service.findByName("test");
        Assertions.assertThat(findDto).isEqualTo(null);
    }

    @Test
    void 중복회원가입체크() {
        createMember();

        // 부캐 가입 방지
        Member newMember=new Member("test", "Ruby I", "test@gmail.com", "12345");
        Long id = service.join(service.toDto(newMember));

        // 중복회원일 경우 id는 -1L을 반환
        Assertions.assertThat(id).isEqualTo(-1L);
    }

    @Test
    void 모든회원조회() {
        createMember();
        service.changePreMemberToMember(1L);

        List<MemberIdDto> members = service.findAllMembers();

        Assertions.assertThat(members.size()).isEqualTo(1);
    }


    @Test
    void findAllPreMember(){
        createMember();
        List<MemberIdDto> allPreMembers = service.findAllPreMembers();
        Assertions.assertThat(allPreMembers.size()).isEqualTo(3);
        Assertions.assertThat(allPreMembers.get(0).getName()).isEqualTo("test");
        Assertions.assertThat(allPreMembers.get(1).getName()).isEqualTo("test2");
        Assertions.assertThat(allPreMembers.get(2).getName()).isEqualTo("test3");
    }
}