package algopa.study.post.domain;

import algopa.study.comment.domain.Comment;
import algopa.study.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
//
//    @OneToMany(mappedBy = "post")
//    private List<Comment> comments=new ArrayList<>();

    private Long views;

    public Post(String title, String contents, Member member, PostPeriod postPeriod) {
        this.title=title;
        this.contents=contents;
        this.member=member;
        this.postPeriod=postPeriod;
        this.member.updateMember(this);
    }

    // update Entity
    public void updatePost(String title, String contents, PostPeriod postPeriod){
        this.title=title;
        this.contents=contents;
        this.postPeriod=postPeriod;
        this.member.updateMember(this);
    }

    public void updateViews(Long views){
        this.views=views+1;
    }
}
