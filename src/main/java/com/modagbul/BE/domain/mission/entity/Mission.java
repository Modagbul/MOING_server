package com.modagbul.BE.domain.mission.entity;

import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.global.entity.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Mission extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_id")
    private Long missionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    private String title;
    private String dueTo;
    private String content;
    private String rule;

    public void createMission(String title, String dueTo, String content, String rule){
        this.title = title;
        this.dueTo = dueTo;
        this.content = content;
        this.rule = rule;
    }

    public void updateMission(String title, String dueTo, String content, String rule){
        this.title = title;
        this.dueTo = dueTo;
        this.content = content;
        this.rule = rule;
    }

}
