package algopa.study.member.service;

import algopa.study.member.domain.Member;
import algopa.study.member.repository.MemberRepository;
import algopa.study.post.domain.Post;
import algopa.study.post.dto.PostIdDto;
import algopa.study.post.mapper.PostIdMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@Getter
@Setter
@RequiredArgsConstructor
public class MemberPostService {
    private final MemberRepository memberRepository;
    private final PostIdMapper postIdMapper= Mappers.getMapper(PostIdMapper.class);

    // 회원이 작성한 게시글 목록
    public List<PostIdDto> findPostsByMember(Long memberId){
        Member member = memberRepository.findById(memberId).get();
        List<Post> posts = member.getPosts();
        return postIdMapper.toDtoList(posts);
    }
}
