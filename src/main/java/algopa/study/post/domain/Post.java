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
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Long views;

    public Post(String title, String contents, Member member) {
        this.title=title;
        this.contents=contents;
        this.member=member;
    }

    // update Entity
    public void updatePost(String title, String contents){
        this.title=title;
        this.contents=contents;
    }

    public void updateViews(Long views){
        this.views=views+1;
    }
}
