package algopa.study.member;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public Member(String name, String tier, String email, String password) {
        this.name = name;
        this.tier = tier;
        this.email=email;
        this.password=password;
    }

    // update Entity
    public void updateMember(String tier, String email){
        this.tier=tier;
        this.email=email;
    }
    public void updateMember(String tier, String email, String password){
        this.tier=tier;
        this.email=email;
        this.password=password;
    }


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> roles=new ArrayList<>();
//        return roles;
//    }
//
//    @Override
//    public String getUsername() {
//        return name;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
