package com.modagbul.BE.domain.mission.service;

import com.modagbul.BE.domain.mission.Exception.MissionAuthDeniedException;
import com.modagbul.BE.domain.mission.dto.MissionDto;
import com.modagbul.BE.domain.mission.entity.Mission;
import com.modagbul.BE.domain.mission.repository.MissionRepository;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.modagbul.BE.domain.mission.constant.MissionConstant.MissionResponseMessage.INVALID_MISSION_ID;

@Service
@RequiredArgsConstructor
public class MissionService {

    @Autowired
    private final MissionRepository missionRepository;
    @Autowired
    private final TeamRepository teamRepository;

    public MissionDto.MissionRes createMission(Long teamId, MissionDto.MissionReq missionReq) {

        //  로그인한 사용자의 id
        Long loginId = 1L;

        // 로그인한 사용자가 소모임장인지 확인 -> 팀 leaderid 확인
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalStateException("해당 팀을 찾을 수 없습니다."));

        if ( team.getLeaderId().equals(loginId)){

            // 소모임장이라면 미션 생성
            Mission newMission = new Mission();
            newMission.createMission(missionReq.getTitle(), missionReq.getDueTo(), missionReq.getContent(), missionReq.getRule());
            Mission savedMission = missionRepository.save(newMission);

            return new MissionDto.MissionRes(savedMission.getTitle(), savedMission.getDueTo(), savedMission.getContent(), savedMission.getRule());

        }
        else{
            // 소모임장이 아니라면 예외 처리
            throw new MissionAuthDeniedException();
        }


    }
    public MissionDto.MissionRes updateMission(Long teamId, Long missionId,MissionDto.MissionReq missionReq) {

        // 소모임장인지 확인
        Long loginId = 1L;

        if (loginId == missionRepository.findLeaderIdByMissionId(missionId).get()) {

            // 잘못된 missionId 예외 처리
            Mission updateMission = missionRepository.findById(missionId).orElseThrow(() -> new IllegalStateException(INVALID_MISSION_ID.getMessage()));

            updateMission.updateMission(missionReq.getTitle(), missionReq.getDueTo(), missionReq.getContent(), missionReq.getRule());
            missionRepository.save(updateMission);

            return new MissionDto.MissionRes(updateMission.getTitle(), updateMission.getDueTo(), updateMission.getContent(), updateMission.getRule());
        }
        else{
            // 소모임장이 아니라면 예외 처리
            throw new MissionAuthDeniedException();
        }
    }


}
