package com.modagbul.BE.domain.noticeread.entity;

import com.modagbul.BE.domain.notice.entity.Notice;
import com.modagbul.BE.domain.teammember.entity.TeamMember;
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
public class NoticeRead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_read_id")
    private Long noticeReadId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private Notice notice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_member_id")
    private TeamMember teamMember;

    private boolean isRead;

    public void setNotice(Notice notice){
        this.notice=notice;
        notice.getNoticeReads().add(this);
    }

    public void setTeamMember(TeamMember teamMember){
        this.teamMember=teamMember;
        teamMember.getNoticeReads().add(this);
    }
}
