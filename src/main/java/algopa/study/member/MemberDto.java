package algopa.study.member;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberDto {
    private String name;
    private String password;
    private String email;
    private String tier;

    public MemberDto(String name, String tier, String email, String password){
        this.name=name;
        this.tier=tier;
        this.email=email;
        this.password=password;
    }
}
