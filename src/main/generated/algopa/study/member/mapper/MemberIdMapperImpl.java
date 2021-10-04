package algopa.study.member.mapper;

import algopa.study.member.domain.Member;
import algopa.study.member.dto.MemberIdDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-10-04T18:11:22+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
@Component
public class MemberIdMapperImpl implements MemberIdMapper {

    @Override
    public MemberIdDto toDto(Member e) {
        if ( e == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String password = null;
        String email = null;
        String tier = null;

        id = e.getId();
        name = e.getName();
        password = e.getPassword();
        email = e.getEmail();
        tier = e.getTier();

        MemberIdDto memberIdDto = new MemberIdDto( id, name, password, email, tier );

        return memberIdDto;
    }

    @Override
    public Member toEntity(MemberIdDto d) {
        if ( d == null ) {
            return null;
        }

        String name = null;
        String tier = null;
        String email = null;
        String password = null;

        name = d.getName();
        tier = d.getTier();
        email = d.getEmail();
        password = d.getPassword();

        Member member = new Member( name, tier, email, password );

        return member;
    }

    @Override
    public List<MemberIdDto> toDtoList(List<Member> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MemberIdDto> list = new ArrayList<MemberIdDto>( entityList.size() );
        for ( Member member : entityList ) {
            list.add( toDto( member ) );
        }

        return list;
    }

    @Override
    public List<Member> toEntityList(List<MemberIdDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Member> list = new ArrayList<Member>( dtoList.size() );
        for ( MemberIdDto memberIdDto : dtoList ) {
            list.add( toEntity( memberIdDto ) );
        }

        return list;
    }

    @Override
    public void updateFromDto(MemberIdDto dto, Member entity) {
        if ( dto == null ) {
            return;
        }
    }
}
