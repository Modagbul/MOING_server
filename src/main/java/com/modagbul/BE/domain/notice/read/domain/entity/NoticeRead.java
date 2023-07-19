package com.modagbul.BE.domain.notice.read.domain.entity;

import com.modagbul.BE.domain.notice.board.domain.entity.Notice;
import com.modagbul.BE.domain.user.entity.User;
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
    @JoinColumn(name = "user_id")
    private User user;

    private boolean isRead;

    public void setNotice(Notice notice){
        this.notice=notice;
        notice.getNoticeReads().add(this);
    }

    public void setUser(User user){
        this.user=user;
        user.getNoticeReads().add(this);
    }

    public void readNotice(){
        this.isRead=true;
    }
}
