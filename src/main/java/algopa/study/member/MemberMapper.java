package algopa.study.member;

import algopa.study.GenericMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface MemberMapper extends GenericMapper<MemberDto, Member> {

}
