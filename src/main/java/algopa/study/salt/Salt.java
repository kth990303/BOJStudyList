package algopa.study.salt;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Salt {
    @Id @GeneratedValue
    private Long id;
    @NotNull
    private String salt;
    public Salt() {}
    public Salt(String salt) {
        this.salt = salt;
    }

}
