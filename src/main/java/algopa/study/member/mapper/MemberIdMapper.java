package algopa.study.member.mapper;

import algopa.study.GenericMapper;
import algopa.study.member.domain.Member;
import algopa.study.member.dto.MemberIdDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberIdMapper extends GenericMapper<MemberIdDto, Member> {

}