package algopa.study.member.mapper;

import algopa.study.member.domain.Member;
import algopa.study.member.dto.MemberDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-23T11:02:06+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberDto toDto(Member arg0) {
        if ( arg0 == null ) {
            return null;
        }

        String name = null;
        String password = null;
        String email = null;
        String tier = null;

        name = arg0.getName();
        password = arg0.getPassword();
        email = arg0.getEmail();
        tier = arg0.getTier();

        MemberDto memberDto = new MemberDto( name, tier, email, password );

        return memberDto;
    }

    @Override
    public List<MemberDto> toDtoList(List<Member> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<MemberDto> list = new ArrayList<MemberDto>( arg0.size() );
        for ( Member member : arg0 ) {
            list.add( toDto( member ) );
        }

        return list;
    }

    @Override
    public List<Member> toEntityList(List<MemberDto> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<Member> list = new ArrayList<Member>( arg0.size() );
        for ( MemberDto memberDto : arg0 ) {
            list.add( toEntity( memberDto ) );
        }

        return list;
    }

    @Override
    public void updateFromDto(MemberDto arg0, Member arg1) {
        if ( arg0 == null ) {
            return;
        }
    }
}
