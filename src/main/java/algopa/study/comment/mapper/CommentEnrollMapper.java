package algopa.study.comment.mapper;

import algopa.study.GenericMapper;
import algopa.study.comment.domain.Comment;
import algopa.study.comment.dto.CommentEnrollDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentEnrollMapper extends GenericMapper<CommentEnrollDto, Comment> {
}
