package com.modagbul.BE.domain.mission.service;

import com.modagbul.BE.domain.mission.Exception.MissionAuthDeniedException;
import com.modagbul.BE.domain.mission.Exception.NotFoundMissionException;
import com.modagbul.BE.domain.mission.dto.MissionDto;
import com.modagbul.BE.domain.mission.dto.MissionMapper;
import com.modagbul.BE.domain.mission.entity.Mission;
import com.modagbul.BE.domain.mission.repository.MissionRepository;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import static com.modagbul.BE.domain.mission.constant.MissionConstant.MissionResponseMessage.INVALID_MISSION_ID;
import static com.modagbul.BE.domain.mission.dto.MissionDto.*;

@Service
@RequiredArgsConstructor
public class MissionService {

//    @Autowired
    private final MissionRepository missionRepository;
//    @Autowired
    private final TeamRepository teamRepository;
    private final MissionMapper missionMapper;

    public MissionRes createMission(Long teamId, MissionReq missionReq) {

        //  로그인한 사용자의 id
        Long loginId = 1L;

        // 로그인한 사용자가 소모임장인지 확인 -> 팀 leaderid 확인
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalStateException("해당 팀을 찾을 수 없습니다."));

        if ( team.getLeaderId().equals(loginId)){
            // 소모임장이라면 미션 생성
            Mission newMission = missionMapper.toEntity(missionReq);
            Mission savedMission = missionRepository.save(newMission);

            return new MissionRes(savedMission.getTitle(), savedMission.getDueTo(), savedMission.getContent(), savedMission.getRule());

        }
        else{
            // 소모임장이 아니라면 예외 처리
            throw new MissionAuthDeniedException();
        }


    }
    public MissionRes updateMission(Long teamId, Long missionId, MissionReq missionReq) {

        // 소모임장인지 확인
        Long loginId = 1L;

        Long findId = missionRepository.findLeaderIdByTeamIdAndMissionId(teamId,missionId).orElseThrow(() -> new NotFoundMissionException());

        if (loginId == findId) {

            // 잘못된 missionId,teamId 예외 처리
            Mission updateMission = missionRepository.findByTeamIdAndMissionId(teamId,missionId).orElseThrow(() -> new NotFoundMissionException());

            updateMission.updateMission(missionReq.getTitle(), missionReq.getDueTo(), missionReq.getContent(), missionReq.getRule());
            missionRepository.save(updateMission);

            return new MissionRes(updateMission.getTitle(), updateMission.getDueTo(), updateMission.getContent(), updateMission.getRule());
        }
        else{
            // 소모임장이 아니라면 예외 처리
            throw new MissionAuthDeniedException();
        }
    }


}
