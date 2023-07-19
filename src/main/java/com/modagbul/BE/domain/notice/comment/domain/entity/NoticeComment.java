package com.modagbul.BE.domain.notice.comment.domain.entity;

import com.modagbul.BE.domain.notice.board.domain.entity.Notice;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.global.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NoticeComment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeCommentId;

    @Lob
    private String content;

    private boolean isDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private Notice notice;

    public void createNoticeComment(String content){
        this.content=content;
    }

    public void deleteNoticeComment(){
        this.isDeleted=true;
    }

    /**
     * 연관관계 매핑
     */
    public void setNotice(Notice notice) {
        this.notice=notice;
        notice.getNoticeComments().add(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.getNoticeComments().add(this);
    }

}
