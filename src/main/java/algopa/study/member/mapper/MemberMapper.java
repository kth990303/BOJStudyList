package algopa.study.member.mapper;

import algopa.study.GenericMapper;
import algopa.study.member.domain.Member;
import algopa.study.member.dto.MemberDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface MemberMapper extends GenericMapper<MemberDto, Member> {
    @Override
    default Member toEntity(MemberDto memberDto){
        if(memberDto==null)
            return null;
        String name=memberDto.getName();
        String tier = memberDto.getTier();
        String email = memberDto.getEmail();
        String password = memberDto.getPassword();

        Member member=new Member(name,tier,email,password);
        member.updateMember(false);

        return member;
    }
}
