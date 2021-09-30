package algopa.study.post.dto;

import algopa.study.member.domain.Member;
import algopa.study.member.dto.MemberDto;
import algopa.study.member.dto.MemberIdDto;
import algopa.study.member.repository.MemberRepository;
import algopa.study.post.domain.PostPeriod;
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
    private Long views;
    private PostPeriod postPeriod;

    public PostIdDto(Long id, String title, String contents, MemberIdDto memberIdDto,
                     Long views, PostPeriod postPeriod) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.memberIdDto = memberIdDto;
        this.views = views;
        this.postPeriod = postPeriod;
    }
}
