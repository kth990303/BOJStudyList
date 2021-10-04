package algopa.study.post.domain;

import algopa.study.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String contents;

    @Embedded
    private PostPeriod postPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Long views;

    public Post(String title, String contents, Member member, PostPeriod postPeriod) {
        this.title=title;
        this.contents=contents;
        this.member=member;
        this.postPeriod=postPeriod;
    }

    // update Entity
    public void updatePost(String title, String contents, PostPeriod postPeriod){
        this.title=title;
        this.contents=contents;
        this.postPeriod=postPeriod;
    }

    public void updateViews(Long views){
        this.views=views+1;
    }
}
