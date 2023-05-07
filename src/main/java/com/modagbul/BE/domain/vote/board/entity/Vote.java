package com.modagbul.BE.domain.vote.board.entity;

import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.vote.comment.entity.VoteComment;
import com.modagbul.BE.domain.vote.content.entity.VoteContent;
import com.modagbul.BE.domain.vote.read.entity.VoteRead;
import com.modagbul.BE.global.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Vote extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Long voteId;

    private String title;

    private String memo;

    //복수투표 여부
    private boolean isMultiple;

    //익명투표 여부
    private boolean isAnonymous;

    //종료 여부
    private boolean isClosed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    //작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usr_id")
    private User user;

    @OneToMany(mappedBy = "vote")
    private List<VoteContent> voteContents=new ArrayList<>();

    @OneToMany(mappedBy = "vote")
    private List<VoteRead> voteReads=new ArrayList<>();

    @OneToMany(mappedBy = "vote")
    private List<VoteComment> voteComments=new ArrayList<>();

    public void createVote(String title, String memo, boolean isAnonymous, boolean isMultiple){
        this.title=title;
        this.memo=memo;
        this.isAnonymous=isAnonymous;
        this.isMultiple=isMultiple;
    }

    public void closeVote() {
        this.isClosed = true;
    }
    /**
     * 연관관계 매핑
     */

    public void setTeam(Team team){
        this.team=team;
        team.getVotes().add(this);
    }

    public void setUser(User user){
        this.user=user;
        user.getVotes().add(this);
    }
}
