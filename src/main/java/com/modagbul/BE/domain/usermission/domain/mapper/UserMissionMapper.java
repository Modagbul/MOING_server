package com.modagbul.BE.domain.usermission.domain.mapper;

import com.modagbul.BE.domain.mission.domain.entity.Mission;
import com.modagbul.BE.domain.usermission.application.dto.UserMissionDetailDto;
import com.modagbul.BE.domain.usermission.domain.entity.UserMission;
import org.springframework.stereotype.Component;

@Component
public class UserMissionMapper {

    public UserMissionDetailDto toDto(UserMission userMission) {
        Mission mission = userMission.getMission();
        UserMissionDetailDto userMissionDetailDto = UserMissionDetailDto.builder()
                .title(mission.getTitle())
                .dueTo(mission.getDueTo())
                .content(mission.getContent())
                .rule(mission.getRule())
                .status(userMission.getStatus())
                .achieve(userMission.getAchieve())
                .build();
        return userMissionDetailDto;

    }
}
