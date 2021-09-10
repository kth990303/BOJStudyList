package algopa.study.post.service;

import algopa.study.post.domain.Post;
import algopa.study.post.dto.PostDto;
import algopa.study.post.mapper.PostMapper;
import algopa.study.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
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
    private final PostMapper postMapper= Mappers.getMapper(PostMapper.class);

    public Long enroll(PostDto postDto){
        return postRepository.save(toEntity(postDto)).getId();
    }
    public PostDto findById(Long id){
        Optional<Post> findPost = postRepository.findById(id);
        if(findPost.isEmpty()){
            throw new NoSuchElementException("게시글이 존재하지 않습니다.");
        }
        return toDto(findPost.get());
    }
    public List<PostDto> findByTitle(String title){
        return findByTitle(title);
    }

    protected PostDto toDto(Post post){
        return postMapper.toDto(post);
    }
    protected Post toEntity(PostDto postDto){
        return postMapper.toEntity(postDto);
    }
}
