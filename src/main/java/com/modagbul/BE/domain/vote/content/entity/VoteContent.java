package com.modagbul.BE.domain.vote.content.entity;

import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.vote.board.entity.Vote;
import com.modagbul.BE.domain.vote.content.user.VoteContentUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//투표 선택지 테이블
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "vote_content")
public class VoteContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_content_id")
    private Long voteContentId;

    private String content;

    //선택한 사람
    @OneToMany(mappedBy = "voteContent")
    private List<VoteContentUser> voteContentUsers=new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_id")
    private Vote vote;

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 연관관계 매핑
     */
    public void setVote(Vote vote) {
        this.vote = vote;
        vote.getVoteContents().add(this);
    }


}
