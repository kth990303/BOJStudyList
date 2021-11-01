package algopa.study.post.service;

import algopa.study.member.domain.Member;
import algopa.study.member.mapper.MemberMapper;
import algopa.study.member.repository.MemberRepository;
import algopa.study.post.domain.Post;
import algopa.study.post.domain.PostPeriod;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public Long enroll(PostDto postDto, String memberName){
        Member loginMember = memberRepository.findByName(memberName);

        // 첫 생성일 땐 생성일자와 수정일자 동일하게
        LocalDateTime now=LocalDateTime.now();
        String creationDate = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd kk:mm:ss"));

        PostPeriod postPeriod = new PostPeriod(creationDate, creationDate);

        Post post=new Post(postDto.getTitle(), postDto.getContents(), loginMember, postPeriod);
        updateViews(post, -1L);
        return postRepository.save(post).getId();
    }
    public void delete(Long id){
        postRepository.deleteById(id);
    }
    public void edit(PostDto postDto, Long id){
        Post post = postRepository.findById(id).orElseThrow(() -> new NoSuchElementException());

        String creationDate=post.getPostPeriod().getCreationDate();
        LocalDateTime now=LocalDateTime.now();
        String editDate = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd kk:mm:ss"));
        PostPeriod postPeriod = new PostPeriod(creationDate, editDate);

        post.updatePost(postDto.getTitle(), postDto.getContents(), postPeriod);
    }
    @Transactional(readOnly = true)
    public PostDto findById(Long id){
        return toDto(postRepository.findById(id).
                orElseThrow(()->new NoSuchElementException("게시글이 존재하지 않습니다.")));
    }
    // postNameDto: 제목을 클릭하면 내용을 보여주는 DTO.
    public PostNameDto findPostNameDtoById(Long id){
        Post post = postRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("게시글이 존재하지 않습니다."));
        // 따라서 조회수를 증가하는 처리를 진행합니다.
        updateViews(post, post.getViews());
        return postNameMapper.toDto(post);
    }
    @Transactional(readOnly = true)
    public List<PostDto> findByTitle(String title){
        return findByTitle(title);
    }
    @Transactional(readOnly = true)
    public List<PostIdDto> findAll(){
        log.info("findAll 호출");
        List<Post> posts = postRepository.findAll();
        return postIdMapper.toDtoList(posts);
    }

    // 조회수 처리_ private
    private Long updateViews(Post post, Long views){
        // 처음 등록 시
        if(views == -1L){
            post.updateViews(views);
            return post.getViews();
        }
        if(!SecurityContextHolder.getContext().getAuthentication().getName().
                equals(post.getMember().getName())){
            post.updateViews(views);
        }
        return post.getViews();
    }

    // DTO <-> Entity
    protected PostDto toDto(Post post){
        return postMapper.toDto(post);
    }
    protected Post toEntity(PostDto postDto){
        return postMapper.toEntity(postDto);
    }
}
