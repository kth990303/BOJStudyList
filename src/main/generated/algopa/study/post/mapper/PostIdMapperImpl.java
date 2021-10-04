package algopa.study.post.mapper;

import algopa.study.post.domain.Post;
import algopa.study.post.dto.PostIdDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-04T18:11:22+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
@Component
public class PostIdMapperImpl implements PostIdMapper {

    @Override
    public List<PostIdDto> toDtoList(List<Post> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<PostIdDto> list = new ArrayList<PostIdDto>( entityList.size() );
        for ( Post post : entityList ) {
            list.add( toDto( post ) );
        }

        return list;
    }

    @Override
    public List<Post> toEntityList(List<PostIdDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Post> list = new ArrayList<Post>( dtoList.size() );
        for ( PostIdDto postIdDto : dtoList ) {
            list.add( toEntity( postIdDto ) );
        }

        return list;
    }

    @Override
    public void updateFromDto(PostIdDto dto, Post entity) {
        if ( dto == null ) {
            return;
        }
    }
}
