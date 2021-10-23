package algopa.study.member.dto;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// 회원가입할 때만 이 DTO를 사용합시다.
public class MemberDto {
    @NotEmpty(message = "이름은 필수입력 대상입니다.")
    private String name;
    @NotEmpty(message = "비밀번호는 필수입력 대상입니다.")
    @Size(min = 2, max = 16, message = "비밀번호는 2자 이상 16자 이하입니다.")
    private String password;
    @NotEmpty(message = "이메일은 필수입력 대상입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;
    private String tier;

    public MemberDto(String name, String tier, String email, String password){
        this.name=name;
        this.tier=tier;
        this.email=email;
        this.password=password;
    }
}
