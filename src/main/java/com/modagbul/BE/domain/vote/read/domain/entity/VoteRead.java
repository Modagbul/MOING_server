package com.modagbul.BE.domain.vote.read.domain.entity;

import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.vote.board.domain.entity.Vote;
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
public class VoteRead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_read_id")
    private Long voteReadId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id")
    private Vote vote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isRead;

    public void setVote(Vote vote){
        this.vote=vote;
        vote.getVoteReads().add(this);
    }

    public void setUser(User user){
        this.user=user;
        user.getVoteReads().add(this);
    }

    public void readVote(){this.isRead=true;}
}
