package com.modagbul.BE.domain.fire.entity;

import com.modagbul.BE.domain.mission.entity.Mission;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.user.entity.User;

import javax.persistence.*;

@Entity
public class Fire {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fireId;

    @OneToOne
    private Team team;
    @OneToOne
    private Mission mission;

    @OneToOne
    private User user;

    @OneToOne
    private User targetUser;






}
