package algopa.study.post.mapper;

import algopa.study.GenericMapper;
import algopa.study.member.domain.Member;
import algopa.study.member.dto.MemberIdDto;
import algopa.study.member.mapper.MemberIdMapper;
import algopa.study.post.domain.Post;
import algopa.study.post.dto.PostIdDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

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

        Post post = new Post( title, contents, member );
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

        PostIdDto postIdDto=new PostIdDto(id, title, contents, memberIdDto);
        return postIdDto;
    }
}
