package algopa.study.post.service;

import algopa.study.member.domain.Member;
import algopa.study.member.dto.MemberDto;
import algopa.study.member.dto.MemberIdDto;
import algopa.study.member.mapper.MemberIdMapper;
import algopa.study.member.mapper.MemberMapper;
import algopa.study.member.repository.MemberRepository;
import algopa.study.post.domain.Post;
import algopa.study.post.dto.PostDto;
import algopa.study.post.dto.PostIdDto;
import algopa.study.post.dto.PostNameDto;
import algopa.study.post.mapper.PostIdMapper;
import algopa.study.post.mapper.PostMapper;
import algopa.study.post.mapper.PostNameMapper;
import algopa.study.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final PostMapper postMapper= Mappers.getMapper(PostMapper.class);
    private final PostIdMapper postIdMapper=Mappers.getMapper(PostIdMapper.class);
    private final PostNameMapper postNameMapper=Mappers.getMapper(PostNameMapper.class);
    private final MemberMapper memberMapper=Mappers.getMapper(MemberMapper.class);

    public Long enroll(PostDto postDto){
        String loginMemberName = SecurityContextHolder.getContext().getAuthentication().getName();
        Member loginMember = memberRepository.findByName(loginMemberName);
        Post post=new Post(postDto.getTitle(), postDto.getContents(), loginMember);
        return postRepository.save(post).getId();
    }
    public PostDto findById(Long id){
        Optional<Post> findPost = postRepository.findById(id);
        if(findPost.isEmpty()){
            throw new NoSuchElementException("게시글이 존재하지 않습니다.");
        }
        return toDto(findPost.get());
    }
    public PostNameDto findPostMemberById(Long id){
        Optional<Post> findPost = postRepository.findById(id);
        if(findPost.isEmpty()){
            throw new NoSuchElementException("게시글이 존재하지 않습니다.");
        }
        return postNameMapper.toDto(findPost.get());
    }
    public List<PostDto> findByTitle(String title){
        return findByTitle(title);
    }
    public List<PostIdDto> findAll(){
        log.info("findAll 호출");
        List<Post> posts = postRepository.findAll();
        return postIdMapper.toDtoList(posts);
    }

    // DTO <-> Entity
    protected PostDto toDto(Post post){
        return postMapper.toDto(post);
    }
    protected Post toEntity(PostDto postDto){
        return postMapper.toEntity(postDto);
    }
}
