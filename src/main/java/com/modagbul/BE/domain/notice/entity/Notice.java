package com.modagbul.BE.domain.notice.entity;

import com.modagbul.BE.domain.noticeread.entity.NoticeRead;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.teammember.entity.TeamMember;
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

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @OneToMany(mappedBy = "notice")
    private List<NoticeRead> noticeReads = new ArrayList<>();

    public void setTeam(Team team){
        this.team=team;
        team.getNotices().add(this);
    }

    public void createNotice(String title, String name){
        this.title=title;
        this.name=name;
    }

}
