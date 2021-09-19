package algopa.study.post.mapper;

import algopa.study.post.domain.Post;
import algopa.study.post.dto.PostIdDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-19T10:40:05+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
@Component
public class PostIdMapperImpl implements PostIdMapper {

    @Override
    public List<PostIdDto> toDtoList(List<Post> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<PostIdDto> list = new ArrayList<PostIdDto>( arg0.size() );
        for ( Post post : arg0 ) {
            list.add( toDto( post ) );
        }

        return list;
    }

    @Override
    public List<Post> toEntityList(List<PostIdDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Post> list = new ArrayList<Post>( arg0.size() );
        for ( PostIdDto postIdDto : arg0 ) {
            list.add( toEntity( postIdDto ) );
        }

        return list;
    }

    @Override
    public void updateFromDto(PostIdDto arg0, Post arg1) {
        if ( arg0 == null ) {
            return;
        }
    }
}
