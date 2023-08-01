package com.modagbul.BE.domain.fire.application.constant;

import com.modagbul.BE.domain.mission.main.domain.entity.Mission;
import lombok.Getter;

@Getter
public class FireFcmMessage {


    private final Mission mission;
    private String title;

    public FireFcmMessage(Mission mission) {
        this.mission = mission;
    }



}
