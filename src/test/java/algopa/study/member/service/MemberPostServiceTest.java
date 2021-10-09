package algopa.study.member.service;

import algopa.study.member.domain.Member;
import algopa.study.member.dto.MemberDto;
import algopa.study.post.domain.Post;
import algopa.study.post.domain.PostPeriod;
import algopa.study.post.dto.PostDto;
import algopa.study.post.dto.PostIdDto;
import algopa.study.post.mapper.PostMapper;
import algopa.study.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
@Rollback(false)
class MemberPostServiceTest {
    @Autowired
    MemberService service;
    @Autowired
    PostService postService;
    @Autowired
    MemberPostService memberPostService;
    @Autowired
    PostMapper postMapper;

    public void createMember() {
        Member member=new Member("test", "Bronze V", "test@naver.com", "123456");
        Member member2=new Member("test2", "Bronze V", "test2@naver.com", "1234");
        Member member3=new Member("test3", "Platinum V", "test@naver.com", "123456");

        service.join(service.toDto(member));
        service.join(service.toDto(member2));
        service.join(service.toDto(member3));
    }
    @Test
    void findPostsByMember() {
        createMember();

        MemberDto memberDto = service.findByName("test");
        Long memberId = service.findIdByName(memberDto.getName());

        PostDto postDto1=new PostDto("new Post1", "hello");
        PostDto postDto2=new PostDto("new Post2", "world");

        postService.enroll(postDto1, "test");
        postService.enroll(postDto2, "test");

        List<PostIdDto> postsByMember = memberPostService.findPostsByMember(memberId);
        Assertions.assertThat(postsByMember.size()).isEqualTo(2);
    }
}