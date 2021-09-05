package algopa.study.member;

import algopa.study.GenericMapper;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface MemberMapper extends GenericMapper<MemberDto, Member> {
    Member toEntity(MemberDto memberDto);
    MemberDto toDto(Member member);
    List<MemberDto> toDtoList(List<Member> entityList);
    List<Member> toEntityList(List<MemberDto> dtoList);
}
