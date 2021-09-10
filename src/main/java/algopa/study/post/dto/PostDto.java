package algopa.study.post.dto;

import algopa.study.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDto {
    private String title;
    private String contents;
    private Member member;

    public PostDto(String title, String contents, Member member){
        this.title=title;
        this.contents=contents;
        this.member=member;
    }
}
