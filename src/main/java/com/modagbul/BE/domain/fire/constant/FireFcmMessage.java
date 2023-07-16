package com.modagbul.BE.domain.fire.constant;

import com.modagbul.BE.domain.mission.domain.entity.Mission;
import lombok.Getter;

@Getter
public class FireFcmMessage {


    private final Mission mission;
    private String title;

    public FireFcmMessage(Mission mission) {
        this.mission = mission;
    }



}
