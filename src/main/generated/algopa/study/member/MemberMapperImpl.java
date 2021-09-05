package algopa.study.member;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-09-05T15:21:23+0900",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.1 (Oracle Corporation)"
)
@Component
public class MemberMapperImpl implements MemberMapper {

    @Override
    public void updateFromDto(MemberDto dto, Member entity) {
        if ( dto == null ) {
            return;
        }
    }

    @Override
    public Member toEntity(MemberDto memberDto) {
        if ( memberDto == null ) {
            return null;
        }

        String name = null;
        String tier = null;
        String email = null;
        String password = null;

        name = memberDto.getName();
        tier = memberDto.getTier();
        email = memberDto.getEmail();
        password = memberDto.getPassword();

        Member member = new Member( name, tier, email, password );

        return member;
    }

    @Override
    public MemberDto toDto(Member member) {
        if ( member == null ) {
            return null;
        }

        MemberDto memberDto = new MemberDto();

        memberDto.setId( member.getId() );
        memberDto.setName( member.getName() );
        memberDto.setPassword( member.getPassword() );
        memberDto.setEmail( member.getEmail() );
        memberDto.setTier( member.getTier() );

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
}
