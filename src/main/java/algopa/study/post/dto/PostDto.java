package algopa.study.post.dto;

import algopa.study.member.domain.Member;
import algopa.study.member.dto.MemberIdDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDto {
    @NotEmpty(message = "제목을 입력해주세요")
    @Size(max = 150, message = "150자 이내로 입력해주세요.")
    private String title;

    @NotEmpty(message = "내용을 입력해주세요")
    @Size(max = 2000, message = "2000자 이내로 입력해주세요.")
    private String contents;

    public PostDto(String title, String contents){
        this.title=title;
        this.contents=contents;
    }
}
