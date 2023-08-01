package com.modagbul.BE.domain.mission.application.service;

import com.modagbul.BE.domain.mission.application.dto.MissionListDto;
import com.modagbul.BE.domain.mission.domain.entity.Mission;
import com.modagbul.BE.domain.mission.domain.repository.MissionRepository;

import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.domain.team.domain.repository.TeamRepository;
import com.modagbul.BE.domain.team.domain.service.TeamQueryService;
import com.modagbul.BE.domain.team_member.domain.repository.TeamMemberRepository;
import com.modagbul.BE.domain.mission.domain.service.MissionQueryService;
import com.modagbul.BE.domain.mission.domain.service.MissionSaveService;
import com.modagbul.BE.domain.mission.exception.MissionAuthDeniedException;
import com.modagbul.BE.domain.mission.exception.NotFoundMissionException;

import com.modagbul.BE.domain.usermission.application.constant.Status;
import com.modagbul.BE.domain.usermission.application.dto.UserMissionDetailDto;
import com.modagbul.BE.domain.usermission.domain.repository.UserMissionRepository;
import com.modagbul.BE.domain.usermission.domain.service.UserMissionQueryService;
import com.modagbul.BE.domain.usermission.domain.service.UserMissionSaveService;
import com.modagbul.BE.domain.usermission.exception.NotFoundUserMissionsException;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.modagbul.BE.domain.mission.application.dto.MissionDto.MissionReq;
import static com.modagbul.BE.domain.mission.application.dto.MissionDto.MissionRes;

@Service
@RequiredArgsConstructor
public class MissionUpdateService {


    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final TeamQueryService teamQueryService;

    private final MissionQueryService missionQueryService;
    private final MissionSaveService missionSaveService;
    private final UserMissionSaveService userMissionSaveService;
    private final UserMissionQueryService userMissionQueryService;



    // 소모임장의 미션 수정
    public MissionRes updateMission(Long teamId, Long missionId, MissionReq missionReq) {

        // 소모임장인지 확인
        if (missionQueryService.isLeader(teamId)){

            Long userId = SecurityUtils.getLoggedInUser().getUserId();
            // 잘못된 missionId,teamId 예외 처리
            Mission updateMission = missionQueryService.getMissionById(teamId, missionId);
            UserMissionDetailDto userMissionDetailDto = userMissionQueryService.getUserMissionDetailById(teamId, missionId);

            updateMission.updateMission(missionReq.getTitle(), missionReq.getDueTo(), missionReq.getContent(), missionReq.getRule());
            missionSaveService.saveMission(updateMission);

            return new MissionRes(updateMission.getTitle(), updateMission.getDueTo(), updateMission.getContent(), updateMission.getRule(),userMissionDetailDto.getStatus());
        }
        else{
            // 소모임장이 아니라면 예외 처리
            throw new MissionAuthDeniedException();
        }
    }



}
