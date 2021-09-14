package algopa.study.post.dto;

import algopa.study.member.domain.Member;
import algopa.study.member.dto.MemberIdDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDto {
    private String title;
    private String contents;

    public PostDto(String title, String contents){
        this.title=title;
        this.contents=contents;
    }
}
