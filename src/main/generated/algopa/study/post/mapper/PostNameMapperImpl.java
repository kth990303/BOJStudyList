package algopa.study.post.mapper;

import algopa.study.post.domain.Post;
import algopa.study.post.dto.PostNameDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-19T10:51:18+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
@Component
public class PostNameMapperImpl implements PostNameMapper {

    @Override
    public List<PostNameDto> toDtoList(List<Post> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<PostNameDto> list = new ArrayList<PostNameDto>( arg0.size() );
        for ( Post post : arg0 ) {
            list.add( toDto( post ) );
        }

        return list;
    }

    @Override
    public List<Post> toEntityList(List<PostNameDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Post> list = new ArrayList<Post>( arg0.size() );
        for ( PostNameDto postNameDto : arg0 ) {
            list.add( toEntity( postNameDto ) );
        }

        return list;
    }

    @Override
    public void updateFromDto(PostNameDto arg0, Post arg1) {
        if ( arg0 == null ) {
            return;
        }
    }
}
