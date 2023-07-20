package com.modagbul.BE.domain.mission.domain.service;

import com.modagbul.BE.domain.mission.application.dto.MissionListDto;
import com.modagbul.BE.domain.mission.domain.entity.Mission;
import com.modagbul.BE.domain.mission.domain.repository.MissionRepository;
import com.modagbul.BE.domain.mission.exception.NotFoundMissionException;
import com.modagbul.BE.domain.usermission.application.constant.Status;
import com.modagbul.BE.domain.usermission.exception.NotFoundUserMissionsException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@DomainService
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MissionQueryService {
    // getMissionById
    private final MissionRepository missionRepository;

    public Mission getMissionById(Long missionId) {
        return missionRepository.findById(missionId).orElseThrow(NotFoundMissionException::new);
    }

    public Optional<List<Mission>> getMissionByTeamId(Long teamId){
        return missionRepository.findMissionsByTeamId(teamId);
    }




    public Long getLeaderIdByTeamIdAndMissionId(Long teamId, Long missionId) {
        return missionRepository.findLeaderIdByTeamIdAndMissionId(teamId, missionId).orElseThrow(NotFoundMissionException::new);
    }

    public List<MissionListDto> getIncompleteMissionListById(Long teamId, Long userId) {
        return missionRepository.findIncompleteMissionListById(teamId, userId, Status.INCOMPLETE).orElseThrow(NotFoundUserMissionsException::new);
    }

    public List<MissionListDto> getCompleteMissionListById(Long teamId, Long userId) {
        return missionRepository.findCompleteMissionListById(teamId, userId, Status.COMPLETE).orElseThrow(NotFoundUserMissionsException::new);
    }

    public List<Mission> getOneDayBeforeDueTo(String criteriaStart, String criteriaEnd){
        return missionRepository.findOneDayBeforeDueTo(criteriaStart,criteriaEnd).orElseThrow();
    }


}
