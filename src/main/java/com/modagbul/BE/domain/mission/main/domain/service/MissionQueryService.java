package com.modagbul.BE.domain.mission.main.domain.service;

import com.modagbul.BE.domain.mission.main.application.dto.MissionListDto;
import com.modagbul.BE.domain.mission.main.domain.entity.Mission;
import com.modagbul.BE.domain.mission.main.domain.repository.MissionRepository;
import com.modagbul.BE.domain.mission.main.exception.NotFoundMissionException;
import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.domain.team.domain.repository.TeamRepository;
import com.modagbul.BE.domain.usermission.application.constant.Status;
import com.modagbul.BE.domain.usermission.exception.NotFoundUserMissionsException;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.modagbul.BE.domain.team.exception.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionQueryService {
    // getMissionById
    private final MissionRepository missionRepository;
    private final TeamRepository teamRepository;

    public Mission getMissionById(Long missionId) {
        return missionRepository.findById(missionId).orElseThrow(NotFoundMissionException::new);
    }

    public List<Mission> getMissionsByTeamId(Long teamId){
        Team team = teamRepository.findById(teamId).orElseThrow(NotFoundTeamIdException::new);
        return team.getMissions();

    }

    public Mission getMissionById(Long teamId, Long missionId) {
        Team team = teamRepository.findById(teamId).orElseThrow(NotFoundTeamIdException::new);
        List<Mission> missions = team.getMissions();
        for (Mission mission : missions) {
            if (mission.getMissionId().equals(missionId)) {
                return mission;
            }
        }
        throw new NotFoundMissionException();
    }

    public List<MissionListDto> getIncompleteMissionListById(Long teamId) {
        Long userId = SecurityUtils.getLoggedInUser().getUserId();
        return missionRepository.findIncompleteMissionListById(teamId, userId, Status.INCOMPLETE).orElseThrow(NotFoundUserMissionsException::new);
    }

    public List<MissionListDto> getCompleteMissionListById(Long teamId) {
        Long userId = SecurityUtils.getLoggedInUser().getUserId();
        return missionRepository.findCompleteMissionListById(teamId, userId, Status.COMPLETE).orElseThrow(NotFoundUserMissionsException::new);
    }

    public List<Mission> getOneDayBeforeDueTo(String criteriaStart, String criteriaEnd){
        return missionRepository.findOneDayBeforeDueTo(criteriaStart,criteriaEnd).orElseThrow();
    }

    public boolean isLeader(Long teamId) {
        Long userId = SecurityUtils.getLoggedInUser().getUserId();
        Long leaderId = teamRepository.findLeaderIdByTeamId(teamId);

        if (userId.equals(leaderId)) {
            return true;
        }
        return false;
    }


}
