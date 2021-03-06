package algopa.study.post.mapper;

import algopa.study.GenericMapper;
import algopa.study.member.domain.Member;
import algopa.study.member.dto.MemberIdDto;
import algopa.study.member.mapper.MemberIdMapper;
import algopa.study.post.domain.Post;
import algopa.study.post.domain.PostPeriod;
import algopa.study.post.dto.PostIdDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Mapper(componentModel = "spring")
public interface PostIdMapper extends GenericMapper<PostIdDto, Post> {
    MemberIdMapper memberIdMapper= Mappers.getMapper(MemberIdMapper.class);

    @Override
    default Post toEntity(PostIdDto postIdDto){
        if ( postIdDto == null ) {
            return null;
        }
        String title = postIdDto.getTitle();
        String contents = postIdDto.getContents();
        Member member = memberIdMapper.toEntity(postIdDto.getMemberIdDto());
        PostPeriod postPeriod = postIdDto.getPostPeriod();

        Post post = new Post( title, contents, member, postPeriod );
        return post;
    }

    default PostIdDto toDto(Post post){
        if(post==null){
            return null;
        }
        Long id=post.getId();
        String title=post.getTitle();
        String contents=post.getContents();
        MemberIdDto memberIdDto=memberIdMapper.toDto(post.getMember());
        Long views=post.getViews();
        PostPeriod postPeriod=post.getPostPeriod();

        PostIdDto postIdDto=new PostIdDto(id, title, contents, memberIdDto, views, postPeriod);
        return postIdDto;
    }
}
