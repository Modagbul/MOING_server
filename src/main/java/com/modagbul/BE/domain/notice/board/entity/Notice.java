package com.modagbul.BE.domain.notice.board.entity;

import com.modagbul.BE.domain.notice.comment.entity.NoticeComment;
import com.modagbul.BE.domain.notice.read.entity.NoticeRead;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.user.entity.User;
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
public class Notice extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long noticeId;

    private String title;

    @Lob
    private String content;

    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    //작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "notice")
    private List<NoticeRead> noticeReads = new ArrayList<>();

    @OneToMany(mappedBy = "notice")
    private List<NoticeComment> noticeComments=new ArrayList<>();

    public void setTeam(Team team){
        this.team=team;
        team.getNotices().add(this);
    }

    public void setUser(User user){
        this.user=user;
        user.getNotices().add(this);
    }

    public void createNotice(String title, String content){
        this.title=title;
        this.content=content;
    }

    public void deleteNotice(){
        this.isDeleted=true;
    }

}
