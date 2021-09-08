package algopa.study.member.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberIdDto {
    private Long id;
    private String name;
    private String password;
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
