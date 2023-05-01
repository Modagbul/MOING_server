package com.modagbul.BE.domain.usermission.entity;

import com.modagbul.BE.domain.mission.entity.Mission;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.usermission.constant.Status;
import com.modagbul.BE.global.annotation.Enum;
import com.modagbul.BE.global.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserMission extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userMissionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String achieve;

    public void setComplete(String achieve){
        this.achieve = achieve;
        this.status = Status.COMPLETE;
    }
    public void setPending(String achieve){
        this.achieve = achieve;
        this.status = Status.PENDING;
    }


}
