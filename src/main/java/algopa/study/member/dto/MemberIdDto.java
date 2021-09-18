package algopa.study.member.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberIdDto {
    @NotNull
    private Long id;
    @NotNull
    private String name;
    @NotNull
    @Size(min = 2, max = 16)
    private String password;
    @Pattern(regexp = "[a-zA-z0-9]+@[a-zA-z]+[.]+[a-zA-z.]+")
    private String email;
    private String tier;

    public MemberIdDto(Long id, String name, String password, String email, String tier) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.tier = tier;
    }
}
