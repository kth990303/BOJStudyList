package algopa.study.post.mapper;

import algopa.study.post.domain.Post;
import algopa.study.post.dto.PostNameDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-18T19:16:18+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
@Component
public class PostNameMapperImpl implements PostNameMapper {

    @Override
    public List<PostNameDto> toDtoList(List<Post> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PostNameDto> list = new ArrayList<PostNameDto>( entityList.size() );
        for ( Post post : entityList ) {
            list.add( toDto( post ) );
        }

        return list;
    }

    @Override
    public List<Post> toEntityList(List<PostNameDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Post> list = new ArrayList<Post>( dtoList.size() );
        for ( PostNameDto postNameDto : dtoList ) {
            list.add( toEntity( postNameDto ) );
        }

        return list;
    }

    @Override
    public void updateFromDto(PostNameDto dto, Post entity) {
        if ( dto == null ) {
            return;
        }
    }
}
