package com.modagbul.BE.domain.user.entity;

import com.modagbul.BE.domain.notice.board.entity.Notice;
import com.modagbul.BE.domain.notice.comment.entity.NoticeComment;
import com.modagbul.BE.domain.notice.read.entity.NoticeRead;
import com.modagbul.BE.domain.team_member.entity.TeamMember;
import com.modagbul.BE.domain.vote.board.entity.Vote;
import com.modagbul.BE.domain.vote.comment.entity.VoteComment;
import com.modagbul.BE.domain.vote.content.user.enttiy.VoteContentUser;
import com.modagbul.BE.domain.vote.read.entity.VoteRead;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.modagbul.BE.domain.user.constant.UserConstant.Role;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String email;

    @Lob
    private String imageUrl;

    private String gender;

    private String ageRange;

    private boolean isDeleted;

    // 추가정보
    private String nickName;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String introduction;

    private String fcmToken;

    @ColumnDefault("true")
    private boolean isNoticePush;

    @ColumnDefault("true")
    private boolean isRemindPush;

    @ColumnDefault("true")
    private boolean isFirePush;

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }


    @OneToMany(mappedBy = "user")
    private List<NoticeComment> noticeComments=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<TeamMember> teamMembers=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<NoticeRead> noticeReads=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<VoteComment> voteComments=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<VoteRead> voteReads=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Notice> notices=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Vote> votes=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<VoteContentUser> voteContentUsers=new ArrayList<>();

    @Builder
    public User(String email, String imageUrl, String gender, String ageRange, Role role){
        this.email=email;
        this.imageUrl=imageUrl;
        this.gender=gender;
        this.ageRange=ageRange;
        this.role=role;
    }

    public void setUser(String nickName, String address, String fcmToken){
        this.nickName=nickName;
        this.address=address;
        this.fcmToken=fcmToken;
    }

    public void setMypage(String nickName, String introduction) {
        this.nickName=nickName;
        this.introduction=introduction;
    }

    public void setDeleted(){
        this.isDeleted=true;
    }

    public void setNoticePush(boolean noticePush) {
        this.isNoticePush = noticePush;
    }

    public void setRemindPush(boolean remindPush) {
        this.isRemindPush = remindPush;
    }

    public void setFirePush(boolean firePush) {
        this.isFirePush = firePush;
    }

}
