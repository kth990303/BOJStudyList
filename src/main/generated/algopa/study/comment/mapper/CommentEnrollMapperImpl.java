package algopa.study.comment.mapper;

import algopa.study.comment.domain.Comment;
import algopa.study.comment.domain.CommentPeriod;
import algopa.study.comment.dto.CommentEnrollDto;
import algopa.study.member.domain.Member;
import algopa.study.post.domain.Post;
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
public class CommentEnrollMapperImpl implements CommentEnrollMapper {

    @Override
    public CommentEnrollDto toDto(Comment e) {
        if ( e == null ) {
            return null;
        }

        String contents = null;

        contents = e.getContents();

        CommentEnrollDto commentEnrollDto = new CommentEnrollDto( contents );

        return commentEnrollDto;
    }

    @Override
    public Comment toEntity(CommentEnrollDto d) {
        if ( d == null ) {
            return null;
        }

        String contents = null;

        contents = d.getContents();

        CommentPeriod commentPeriod = null;
        Member member = null;
        Post post = null;

        Comment comment = new Comment( contents, commentPeriod, member, post );

        return comment;
    }

    @Override
    public List<CommentEnrollDto> toDtoList(List<Comment> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<CommentEnrollDto> list = new ArrayList<CommentEnrollDto>( entityList.size() );
        for ( Comment comment : entityList ) {
            list.add( toDto( comment ) );
        }

        return list;
    }

    @Override
    public List<Comment> toEntityList(List<CommentEnrollDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Comment> list = new ArrayList<Comment>( dtoList.size() );
        for ( CommentEnrollDto commentEnrollDto : dtoList ) {
            list.add( toEntity( commentEnrollDto ) );
        }

        return list;
    }

    @Override
    public void updateFromDto(CommentEnrollDto dto, Comment entity) {
        if ( dto == null ) {
            return;
        }
    }
}
