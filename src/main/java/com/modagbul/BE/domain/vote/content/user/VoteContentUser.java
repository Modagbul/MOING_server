package com.modagbul.BE.domain.vote.content.user;

import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.vote.content.entity.VoteContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//투표 선택한 사람 테이블
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "vote_content_user")
public class VoteContentUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_content_user_id")
    private Long voteContentUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vote_content_id")
    private VoteContent voteContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;


    /**
     * 연관관계 매핑
     */
    public void setVoteContent(VoteContent voteContent){
        this.voteContent=voteContent;
        voteContent.getVoteContentUsers().add(this);
    }

    public void setUser(User user){
        this.user=user;
        user.getVoteContentUsers().add(this);
    }
}
