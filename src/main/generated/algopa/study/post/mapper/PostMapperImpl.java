package algopa.study.post.mapper;

import algopa.study.member.domain.Member;
import algopa.study.post.domain.Post;
import algopa.study.post.domain.PostPeriod;
import algopa.study.post.dto.PostDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-20T19:18:19+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public PostDto toDto(Post e) {
        if ( e == null ) {
            return null;
        }

        String title = null;
        String contents = null;

        title = e.getTitle();
        contents = e.getContents();

        PostDto postDto = new PostDto( title, contents );

        return postDto;
    }

    @Override
    public Post toEntity(PostDto d) {
        if ( d == null ) {
            return null;
        }

        String title = null;
        String contents = null;

        title = d.getTitle();
        contents = d.getContents();

        Member member = null;
        PostPeriod postPeriod = null;

        Post post = new Post( title, contents, member, postPeriod );

        return post;
    }

    @Override
    public List<PostDto> toDtoList(List<Post> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PostDto> list = new ArrayList<PostDto>( entityList.size() );
        for ( Post post : entityList ) {
            list.add( toDto( post ) );
        }

        return list;
    }

    @Override
    public List<Post> toEntityList(List<PostDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Post> list = new ArrayList<Post>( dtoList.size() );
        for ( PostDto postDto : dtoList ) {
            list.add( toEntity( postDto ) );
        }

        return list;
    }

    @Override
    public void updateFromDto(PostDto dto, Post entity) {
        if ( dto == null ) {
            return;
        }
    }
}
