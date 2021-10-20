package algopa.study.member.mapper;

import algopa.study.member.domain.Member;
import algopa.study.member.dto.MemberDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-20T19:18:18+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public MemberDto toDto(Member e) {
        if ( e == null ) {
            return null;
        }

        String name = null;
        String password = null;
        String email = null;
        String tier = null;

        name = e.getName();
        password = e.getPassword();
        email = e.getEmail();
        tier = e.getTier();

        MemberDto memberDto = new MemberDto( name, tier, email, password );

        return memberDto;
    }

    @Override
    public List<MemberDto> toDtoList(List<Member> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MemberDto> list = new ArrayList<MemberDto>( entityList.size() );
        for ( Member member : entityList ) {
            list.add( toDto( member ) );
        }

        return list;
    }

    @Override
    public List<Member> toEntityList(List<MemberDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Member> list = new ArrayList<Member>( dtoList.size() );
        for ( MemberDto memberDto : dtoList ) {
            list.add( toEntity( memberDto ) );
        }

        return list;
    }

    @Override
    public void updateFromDto(MemberDto dto, Member entity) {
        if ( dto == null ) {
            return;
        }
    }
}
