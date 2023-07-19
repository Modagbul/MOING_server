package com.modagbul.BE.domain.vote.comment.domain.entity;

import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.vote.board.domain.entity.Vote;
import com.modagbul.BE.global.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class VoteComment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_comment_id")
    private Long voteCommentId;

    @Lob
    private String content;

    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id")
    private Vote vote;

    public void createVoteComment(String content) {
        this.content = content;
    }

    public void deleteVoteComment() {
        this.isDeleted = true;
    }

    /**
     * 연관관계 매핑
     */
    public void setVote(Vote vote) {
        this.vote = vote;
        vote.getVoteComments().add(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.getVoteComments().add(this);
    }
}
