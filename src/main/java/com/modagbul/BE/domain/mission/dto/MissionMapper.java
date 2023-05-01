package com.modagbul.BE.domain.mission.dto;

import com.modagbul.BE.domain.mission.dto.MissionDto.MissionReq;
import com.modagbul.BE.domain.mission.entity.Mission;
import org.springframework.stereotype.Component;

@Component
public class MissionMapper {

    public Mission toEntity(MissionReq missionReq){
        Mission mission = new Mission();
        mission.createMission(
                missionReq.getTitle(),
                missionReq.getDueTo(),
                missionReq.getContent(),
                missionReq.getRule()
        );
        return mission;
    }

}
