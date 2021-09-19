package algopa.study.post.controller;

import algopa.study.member.service.MemberService;
import algopa.study.post.dto.PostDto;
import algopa.study.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Slf4j
@Rollback
class PostControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberService memberService;
    @Autowired
    PostService postService;

    @Retention(RetentionPolicy.RUNTIME)
    @WithMockUser(username = "kth990303", roles = "ADMIN")
    public @interface WithAdminUser{}

    @Retention(RetentionPolicy.RUNTIME)
    @WithMockUser(username="choisk0531", roles = "USER")
    public @interface WithUser{}

    @Test
    @WithAnonymousUser
    void home() throws Exception {
        mockMvc.perform(get("/post/"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @WithUser
    void enrollPost() throws Exception{
        MultiValueMap<String, String> postDto=new LinkedMultiValueMap<>();

        postDto.add("title", "hello");
        postDto.add("contents", "my post");

        mockMvc.perform(post("/post/enroll").params(postDto))
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }

    @Test
    void enroll() {
    }

    @Test
    void enterPost() {
    }

    @Test
    void deletePost() {
    }
}