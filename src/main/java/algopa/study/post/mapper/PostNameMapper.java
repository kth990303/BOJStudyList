package algopa.study.post.mapper;

import algopa.study.GenericMapper;
import algopa.study.member.domain.Member;
import algopa.study.member.dto.MemberDto;
import algopa.study.member.mapper.MemberMapper;
import algopa.study.post.domain.Post;
import algopa.study.post.domain.PostPeriod;
import algopa.study.post.dto.PostNameDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface PostNameMapper extends GenericMapper<PostNameDto, Post> {
    MemberMapper memberMapper= Mappers.getMapper(MemberMapper.class);
    @Override
    default Post toEntity(PostNameDto postNameDto){
        if ( postNameDto == null ) {
            return null;
        }
        String title = postNameDto.getTitle();
        String contents = postNameDto.getContents();
        Member member = memberMapper.toEntity(postNameDto.getMemberDto());
        PostPeriod postPeriod = postNameDto.getPostPeriod();

        Post post = new Post( title, contents, member, postPeriod );
        return post;
    }

    default PostNameDto toDto(Post post){
        if(post==null){
            return null;
        }
        String title=post.getTitle();
        String contents=post.getContents();
        MemberDto memberDto=memberMapper.toDto(post.getMember());
        Long views=post.getViews();
        PostPeriod postPeriod=post.getPostPeriod();

        PostNameDto postNameDto=new PostNameDto(title, contents, memberDto, views, postPeriod);
        return postNameDto;
    }
}
