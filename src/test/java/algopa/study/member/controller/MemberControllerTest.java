package algopa.study.member.controller;

import algopa.study.member.domain.Member;
import algopa.study.member.dto.MemberDto;
import algopa.study.member.service.MemberService;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberService memberService;

    @Retention(RetentionPolicy.RUNTIME)
    @WithMockUser(username = "kth990303", roles = "ADMIN")
    public @interface WithAdminUser{}

    @Retention(RetentionPolicy.RUNTIME)
    @WithMockUser(username="choisk0531", roles = "USER")
    public @interface WithUser{}


    @Test
    @WithAnonymousUser
    public void 메인화면() throws Exception{
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    @Transactional
    public void 로그인성공() throws Exception{
        String username = "test";
        String tier="Silver I";
        String email="test@gmail.com";
        String password = "12345";

        MemberDto member=new MemberDto(username, tier, email, password);
        memberService.join(member);
        // 성공 TEST
        mockMvc.perform(formLogin().user(member.getName()).password(password))
                .andDo(print())
                .andExpect(authenticated());
    }

    @Test
    @Transactional
    public void 로그인실패() throws Exception{
        String username = "test";
        String tier="Silver I";
        String email="test@gmail.com";
        String password = "12345";

        MemberDto member=new MemberDto(username, tier, email, password);
        memberService.join(member);
        // 실패 TEST
        mockMvc.perform(formLogin().user(member.getName()).password("123456"))
                .andExpect(unauthenticated());
    }

    @Test
    @WithUser
    public void 로그아웃() throws Exception{
        mockMvc.perform(get("/logout"))
                .andDo(print())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    @WithUser
    public void 내정보() throws Exception{
        mockMvc.perform(get("/myInfo"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("myInfo"));
    }

    @Test
    @WithAnonymousUser
    public void 회원가입() throws Exception{
        // 회원가입 창으로 잘 가지는지
        // 로직 test는 serviceTest에서
        mockMvc.perform(get("/createMemberForm"))
                .andDo(print())
                .andExpect(view().name("createMemberForm"));
    }

    @Test
    @WithMockUser(username = "test", roles = "USER")
    @Transactional
    public void 정보수정() throws Exception{
        String username = "test";
        String tier="Silver I";
        String email="test@gmail.com";
        String password = "12345";

        MemberDto member=new MemberDto(username, tier, email, password);
        memberService.join(member);


        Long id = memberService.findIdByName(member.getName());
        MemberDto member2=new MemberDto(username, "Bronze V", email, password);

        Long changeId = memberService.edit(id, member2);
        MemberDto memberDto = memberService.findById(changeId);
        Assertions.assertThat(memberDto.getTier()).isEqualTo(member2.getTier());

    }
}

