package algopa.study.post.dto;

import algopa.study.member.domain.Member;
import algopa.study.member.dto.MemberDto;
import algopa.study.post.domain.PostPeriod;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostNameDto {
    private String title;
    private String contents;
    private MemberDto memberDto;
    private Long views;
    private PostPeriod postPeriod;

    public PostNameDto(String title, String contents, MemberDto memberDto,
                       Long views, PostPeriod postPeriod) {
        this.title = title;
        this.contents = contents;
        this.memberDto = memberDto;
        this.views = views;
        this.postPeriod = postPeriod;
    }
}
