package algopa.study.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberRole {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");
    private final String value;
}
