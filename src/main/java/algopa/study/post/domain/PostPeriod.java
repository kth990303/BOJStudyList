package algopa.study.post.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class PostPeriod {
    private String creationDate;
    private String editDate;

    public PostPeriod() {
    }

    public PostPeriod(String creationDate, String editDate) {
        this.creationDate = creationDate;
        this.editDate = editDate;
    }
}
