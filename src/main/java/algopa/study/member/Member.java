package algopa.study.member;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    @NotNull
    private String name;
    @NotNull
    private String password;
    @NotNull
    private String email;
    @NotNull
    private String tier;

    public Member(String name, String tier, String email) {
        this.name = name;
        this.tier = tier;
        this.email=email;
        this.password="0";
    }

    public Member(String name, String tier, String email, String password) {
        this.name = name;
        this.tier = tier;
        this.email=email;
        this.password=password;
    }
}
