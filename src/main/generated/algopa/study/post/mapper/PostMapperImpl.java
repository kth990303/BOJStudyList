package algopa.study.post.mapper;

import algopa.study.member.domain.Member;
import algopa.study.post.domain.Post;
import algopa.study.post.dto.PostDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-10T23:01:15+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostDto toDto(Post arg0) {
        if ( arg0 == null ) {
            return null;
        }

        String title = null;
        String contents = null;
        Member member = null;

        title = arg0.getTitle();
        contents = arg0.getContents();
        member = arg0.getMember();

        PostDto postDto = new PostDto( title, contents );

        return postDto;
    }

    @Override
    public Post toEntity(PostDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        String title = null;
        String contents = null;
        Member member = null;

        title = arg0.getTitle();
        contents = arg0.getContents();

        Post post = new Post( title, contents, member );

        return post;
    }

    @Override
    public List<PostDto> toDtoList(List<Post> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<PostDto> list = new ArrayList<PostDto>( arg0.size() );
        for ( Post post : arg0 ) {
            list.add( toDto( post ) );
        }

        return list;
    }

    @Override
    public List<Post> toEntityList(List<PostDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Post> list = new ArrayList<Post>( arg0.size() );
        for ( PostDto postDto : arg0 ) {
            list.add( toEntity( postDto ) );
        }

        return list;
    }

    @Override
    public void updateFromDto(PostDto arg0, Post arg1) {
        if ( arg0 == null ) {
            return;
        }
    }
}
