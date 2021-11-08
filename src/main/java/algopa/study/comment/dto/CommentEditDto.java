package algopa.study.comment.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CommentEditDto {
    @NotEmpty(message = "내용을 입력해주세요")
    @Size(max = 500, message = "500자 이내로 입력해주세요.")
    private String contents;
}
