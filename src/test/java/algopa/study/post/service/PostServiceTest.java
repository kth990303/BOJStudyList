package algopa.study.post.service;

import algopa.study.member.domain.Member;
import algopa.study.member.dto.MemberDto;
import algopa.study.member.mapper.MemberMapper;
import algopa.study.member.service.MemberService;
import algopa.study.post.domain.Post;
import algopa.study.post.dto.PostDto;
import org.assertj.core.api.Assertions;
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
class PostServiceTest {
    @Autowired
    PostService postService;
    @Autowired
    MemberService memberService;
    @Autowired
    MemberMapper memberMapper;


}