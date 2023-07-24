package com.modagbul.BE.domain.usermission.domain.service;

import com.modagbul.BE.domain.mission.domain.entity.Mission;
import com.modagbul.BE.domain.mission.exception.NotFoundMissionException;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.usermission.application.constant.Status;
import com.modagbul.BE.domain.usermission.application.dto.UserMissionDetailDto;

import com.modagbul.BE.domain.usermission.application.dto.UserMissionListDto;
import com.modagbul.BE.domain.usermission.domain.entity.UserMission;
import com.modagbul.BE.domain.usermission.domain.repository.UserMissionRepository;
import com.modagbul.BE.domain.usermission.exception.NotFoundUserMissionsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserMissionQueryService {

    private final UserMissionRepository userMissionRepository;

    public UserMission getUserMissionById(Long userMissionId,Long teamId, Long missionId) {
        return userMissionRepository.findUserMissionById(userMissionId,teamId,missionId).orElseThrow(NotFoundMissionException::new);
    }


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


    public UserMission getUserMissionById(Long userMissionId) {
        return userMissionRepository.findById(userMissionId).orElseThrow(NotFoundUserMissionsException::new);
    }


    public List<UserMissionListDto> getCompleteUserMission(Long teamId, Long missionId, Status status) {
        return userMissionRepository.findCompleteUserMissionListById(teamId, missionId, status).orElseThrow(NotFoundUserMissionsException::new);
    }
    public List<UserMissionListDto> getInCompleteUserMission(Long teamId, Long missionId, Status status) {
        return userMissionRepository.findCompleteUserMissionListById(teamId, missionId, status).orElseThrow(NotFoundUserMissionsException::new);
    }




}
