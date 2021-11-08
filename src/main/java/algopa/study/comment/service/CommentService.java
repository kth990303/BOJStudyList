package algopa.study.comment.service;

import algopa.study.comment.domain.Comment;
import algopa.study.comment.domain.CommentPeriod;
import algopa.study.comment.dto.CommentEditDto;
import algopa.study.comment.dto.CommentEnrollDto;
import algopa.study.comment.mapper.CommentEnrollMapper;
import algopa.study.comment.repository.CommentRepository;
import algopa.study.member.domain.Member;
import algopa.study.member.repository.MemberRepository;
import algopa.study.post.domain.Post;
import algopa.study.post.domain.PostPeriod;
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
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final CommentEnrollMapper commentEnrollMapper
            = Mappers.getMapper(CommentEnrollMapper.class);

    public Long enroll(CommentEnrollDto commentEnrollDto, Long postId){
        String loginMemberName = SecurityContextHolder.getContext().getAuthentication().getName();
        Member loginMember = memberRepository.findByName(loginMemberName);

        Post post = postRepository.findById(postId).get();

        // 첫 생성일 땐 생성일자와 수정일자 동일하게
        LocalDateTime now=LocalDateTime.now();
        String creationDate = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd kk:mm:ss"));

        CommentPeriod commentPeriod = new CommentPeriod(creationDate, creationDate);

        Comment comment=
                new Comment(commentEnrollDto.getContents(), commentPeriod, loginMember, post);
        return commentRepository.save(comment).getId();
    }
//    // 특정 post의 모든 댓글을 가져온다.
//    @Transactional(readOnly = true)
//    public List<CommentEnrollDto> findAll(Long postId){
//        Post post = postRepository.findById(postId).get();
//        List<Comment> comments = post.getComments();
//        return commentEnrollMapper.toDtoList(comments);
//    }
    public void delete(Long id){
        commentRepository.deleteById(id);
    }
    public void edit(CommentEditDto commentEditDto, Long commentId) {

    }
    // enrollDto <-> Entity
    protected CommentEnrollDto toEnrollDto(Comment comment){
        return commentEnrollMapper.toDto(comment);
    }
    protected Comment toEntity(CommentEnrollDto commentEnrollDto){
        return commentEnrollMapper.toEntity(commentEnrollDto);
    }
}
