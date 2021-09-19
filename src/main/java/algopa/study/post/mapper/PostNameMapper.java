package algopa.study.post.mapper;

import algopa.study.GenericMapper;
import algopa.study.member.domain.Member;
import algopa.study.member.dto.MemberDto;
import algopa.study.member.mapper.MemberMapper;
import algopa.study.post.domain.Post;
import algopa.study.post.dto.PostNameDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

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

        Post post = new Post( title, contents, member );
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

        PostNameDto postNameDto=new PostNameDto(title, contents, memberDto, views);
        return postNameDto;
    }
}
