package com.modagbul.BE.domain.usermission.domain.service;

import com.modagbul.BE.domain.mission.main.domain.entity.Mission;
import com.modagbul.BE.domain.mission.main.domain.service.MissionQueryService;
import com.modagbul.BE.domain.mission.main.exception.NotFoundMissionException;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.usermission.application.constant.Status;
import com.modagbul.BE.domain.usermission.application.dto.UserMissionDetailDto;

import com.modagbul.BE.domain.usermission.application.dto.UserMissionListDto;
import com.modagbul.BE.domain.usermission.domain.entity.UserMission;
import com.modagbul.BE.domain.usermission.domain.mapper.UserMissionMapper;
import com.modagbul.BE.domain.usermission.domain.repository.UserMissionRepository;
import com.modagbul.BE.domain.usermission.exception.NotFoundUserMissionsException;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserMissionQueryService {

    private final UserMissionRepository userMissionRepository;
    private final MissionQueryService missionQueryService;
    private final UserMissionMapper userMissionMapper;

    public List<UserMission> getUserMissionsById(Long teamId, Long missionId) {
        Long userId = SecurityUtils.getLoggedInUser().getUserId();
        Mission findMission = missionQueryService.getMissionById(teamId, missionId);
        return findMission.getUserMissions();

    }

    public UserMission getUserMissionById(Long teamId, Long missionId) {

        List<UserMission> userMissions = getUserMissionsById(teamId, missionId);

        for (UserMission userMission : userMissions) {
            if (userMission.getTeam().getTeamId().equals(teamId) && userMission.getMission().getMissionId().equals(missionId)) {
                return userMission;
            }
        }

        throw new NotFoundMissionException();

    }

    public UserMissionDetailDto getUserMissionDetailById(Long teamId, Long missionId) {

        UserMission findUserMission = getUserMissionById(teamId, missionId);
        return userMissionMapper.toDto(findUserMission);

    }

    public List<User> getInCompleteUsersByMission(Mission mission){

        return userMissionRepository.getInCompleteUsersByMission(mission).orElseThrow(NotFoundMissionException::new);
    }

    public Long getPersonalRateForGraph(Long teamId,Long loginId) {
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
