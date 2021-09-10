package algopa.study.post.mapper;

import algopa.study.GenericMapper;
import algopa.study.post.domain.Post;
import algopa.study.post.dto.PostDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper extends GenericMapper<PostDto, Post> {
}
