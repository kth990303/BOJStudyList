package algopa.study.comment.mapper;

import algopa.study.GenericMapper;
import algopa.study.comment.domain.Comment;
import algopa.study.comment.dto.CommentEditDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentEditMapper extends GenericMapper<CommentEditDto, Comment> {
}
