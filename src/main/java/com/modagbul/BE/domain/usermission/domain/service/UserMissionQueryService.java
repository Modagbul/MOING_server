package com.modagbul.BE.domain.usermission.domain.service;

import com.modagbul.BE.domain.mission.domain.entity.Mission;
import com.modagbul.BE.domain.mission.exception.NotFoundMissionException;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.usermission.application.dto.UserMissionDetailDto;
import com.modagbul.BE.domain.usermission.domain.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserMissionQueryService {

    private final UserMissionRepository userMissionRepository;


    public UserMissionDetailDto getUserMissionDetailById(Long teamId, Long userId, Long missionId) {
        return userMissionRepository.findUserMissionDetailById(teamId, userId, missionId).orElseThrow(NotFoundMissionException::new);
    }

    public List<User> getInCompleteUsersByMission(Mission mission){
        return userMissionRepository.getInCompleteUsersByMission(mission).orElseThrow(NotFoundMissionException::new);
    }

    public Long getPersonalRateForGraph(Long teamId,Long loginId) {
//        return userMissionRepository.getPersonalRateForGraphById(loginId, teamId).orElseThrow(InvalidCompleteRateException::new);
        return userMissionRepository.getPersonalRateForGraphById(loginId, teamId).orElse(0L);
    }




}
