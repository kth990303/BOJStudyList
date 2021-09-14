package algopa.study.post.dto;

import algopa.study.member.domain.Member;
import algopa.study.member.dto.MemberDto;
import algopa.study.member.dto.MemberIdDto;
import algopa.study.member.repository.MemberRepository;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostIdDto {
    private Long id;
    private String title;
    private String contents;
    private MemberIdDto memberIdDto;

    public PostIdDto(Long id, String title, String contents, MemberIdDto memberIdDto){
        this.id=id;
        this.title=title;
        this.contents=contents;
        this.memberIdDto=memberIdDto;
    }
}
