package com.modagbul.BE.domain.usermission.domain.entity;


import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.domain.mission.domain.entity.Mission;

import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.usermission.application.constant.Status;
import com.modagbul.BE.global.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;


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

    public UserMission createUserMission(User user, Team team, Mission mission) {
        this.user = user;
        this.team = team;
        this.mission = mission;
        this.status = Status.INCOMPLETE;
        return this;
    }

    public void setComplete(String achieve){
        this.achieve = achieve;
        this.status = Status.COMPLETE;
    }

    public void setPending(String achieve){
        this.achieve = achieve;
        this.status = Status.PENDING;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserMission)) return false;
        UserMission that = (UserMission) o;
        return status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status);
    }
}
