package algopa.study.comment.domain;

import algopa.study.member.domain.Member;
import algopa.study.post.domain.Post;
import algopa.study.post.domain.PostPeriod;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String contents;

    @Embedded
    private CommentPeriod commentPeriod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;


    public Comment(String contents, CommentPeriod commentPeriod, Member member, Post post){
        this.contents=contents;
        this.commentPeriod=commentPeriod;
        this.member=member;
        this.post=post;
    }
    public void updateComment(String contents, CommentPeriod commentPeriod){
        this.contents=contents;
        this.commentPeriod=commentPeriod;
    }
}
