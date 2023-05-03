package com.modagbul.BE.domain.mission.service;

import com.modagbul.BE.domain.mission.Exception.MissionAuthDeniedException;
import com.modagbul.BE.domain.mission.Exception.NotFoundMissionException;
import com.modagbul.BE.domain.mission.dto.MissionDetailDto;
import com.modagbul.BE.domain.mission.dto.MissionDto;
import com.modagbul.BE.domain.mission.dto.MissionListDto;
import com.modagbul.BE.domain.mission.entity.Mission;
import com.modagbul.BE.domain.mission.repository.MissionRepository;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.team.repository.TeamRepository;
import com.modagbul.BE.domain.usermission.constant.Status;
import com.modagbul.BE.domain.usermission.dto.UserMissionDetailDto;
import com.modagbul.BE.domain.usermission.exception.NotFoundUserMissionsException;
import com.modagbul.BE.domain.usermission.repository.UserMissionRepository;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

import static com.modagbul.BE.domain.mission.constant.MissionConstant.MissionResponseMessage.INVALID_MISSION_ID;

import static com.modagbul.BE.domain.mission.dto.MissionDto.*;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final TeamRepository teamRepository;

    // 소모임장의 미션 생성
    public MissionRes createMission(Long teamId, MissionReq missionReq) {

        //  로그인한 사용자의 id
        Long loginId = SecurityUtils.getLoggedInUser().getUserId();

        // 로그인한 사용자가 소모임장인지 확인 -> 팀 leaderid 확인
        Team findteam = teamRepository.findById(teamId).orElseThrow(() -> new IllegalStateException("해당 팀을 찾을 수 없습니다."));

        if ( findteam.getLeaderId().equals(loginId)){
            // 소모임장이라면 미션 생성
            Mission newMission = new Mission();
            newMission.createMission(
                    findteam,
                    missionReq.getTitle(),
                    missionReq.getDueTo(),
                    missionReq.getContent(),
                    missionReq.getRule()

            );
            Mission savedMission = missionRepository.save(newMission);

            return new MissionRes(savedMission.getTitle(), savedMission.getDueTo(), savedMission.getContent(), savedMission.getRule(), Status.INCOMPLETE);

        }
        else{
            // 소모임장이 아니라면 예외 처리
            throw new MissionAuthDeniedException();
        }


    }

    // 소모임장의 미션 수정
    public MissionRes updateMission(Long teamId, Long missionId, MissionReq missionReq) {

        // 소모임장인지 확인
        Long userId = SecurityUtils.getLoggedInUser().getUserId();

        Long findId = missionRepository.findLeaderIdByTeamIdAndMissionId(teamId,missionId).orElseThrow(NotFoundMissionException::new);

        if (userId.equals(findId)) {

            // 잘못된 missionId,teamId 예외 처리
            Mission updateMission = missionRepository.findByTeamIdAndMissionId(teamId, missionId).orElseThrow(NotFoundMissionException::new);
            UserMissionDetailDto userMissionDetailDto = userMissionRepository.findUserMissionDetailById(teamId, userId, missionId).orElseThrow(NotFoundMissionException::new);

            updateMission.updateMission(missionReq.getTitle(), missionReq.getDueTo(), missionReq.getContent(), missionReq.getRule());
            missionRepository.save(updateMission);

            return new MissionRes(updateMission.getTitle(), updateMission.getDueTo(), updateMission.getContent(), updateMission.getRule(),userMissionDetailDto.getStatus());
        }
        else{
            // 소모임장이 아니라면 예외 처리
            throw new MissionAuthDeniedException();
        }
    }

    // 개인별 미션 리스트 조회
    public List<MissionListDto> getMissionList(Long teamId){

        Long userId = SecurityUtils.getLoggedInUser().getUserId();
        List<MissionListDto> missionListDtos = missionRepository.findMissionListById(teamId).orElseThrow(NotFoundUserMissionsException::new);
        for (MissionListDto missionListDto : missionListDtos) {
            missionListDto.setStatus(userMissionRepository.findUserMissionStatusById(userId,teamId, missionListDto.getMissionId()).orElse(Status.INCOMPLETE));
        }
        return missionListDtos;

    }

    // 개인별 미션 상세 페이지 조회
    public MissionDetailDto getMissionDetail(Long teamId, Long missionId) {
        Long userId = SecurityUtils.getLoggedInUser().getUserId();
        MissionDetailDto missionDetailDto = missionRepository.findMissionDetailById(teamId, missionId).orElseThrow(NotFoundMissionException::new);
        missionDetailDto.setStatus(userMissionRepository.findUserMissionStatusById(userId, teamId, missionId).orElse(Status.INCOMPLETE));
        return missionDetailDto;
    }


}
