package algopa.study.post.service;

import algopa.study.member.dto.MemberDto;
import algopa.study.member.service.MemberService;
import algopa.study.post.domain.Post;
import algopa.study.post.dto.PostDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback
class PostServiceTest {
    @Autowired
    PostService postService;
    @Autowired
    MemberService memberService;

    @Test
    void enroll() {
        MemberDto memberDto=new MemberDto("test", "Silver I", "test@naver.com", "123456");
        memberService.join(memberDto);

    }
}