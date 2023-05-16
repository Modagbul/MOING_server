package com.modagbul.BE.domain.fire.constant;

import com.modagbul.BE.domain.mission.entity.Mission;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class FireFcmMessage {


    private final Mission mission;
    private String title;

    public FireFcmMessage(Mission mission) {
        this.mission = mission;
    }



}
