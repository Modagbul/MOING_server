package com.modagbul.BE.domain.fire.entity;

import com.modagbul.BE.domain.mission.entity.Mission;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.usermission.entity.UserMission;

import javax.persistence.*;

@Entity
public class Fire {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fireId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_mission_id")
    private UserMission targetUserMission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User throwUser;

    public void createFire(UserMission targetUserMission, User throwUser){
        this.targetUserMission = targetUserMission;
        this.throwUser = throwUser;
    }


}
