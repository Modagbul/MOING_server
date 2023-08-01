package com.modagbul.BE.domain.mission.main.application.service;

import com.modagbul.BE.domain.mission.main.application.dto.MissionReq;
import com.modagbul.BE.domain.mission.main.application.dto.MissionRes;
import com.modagbul.BE.domain.mission.main.domain.entity.Mission;
import com.modagbul.BE.domain.mission.main.domain.service.MissionQueryService;
import com.modagbul.BE.domain.mission.main.domain.service.MissionSaveService;
import com.modagbul.BE.domain.mission.main.exception.InvalidDueToDate;
import com.modagbul.BE.domain.mission.main.exception.MissionAuthDeniedException;
import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.domain.team.domain.repository.TeamRepository;
import com.modagbul.BE.domain.team.domain.service.TeamQueryService;
import com.modagbul.BE.domain.team_member.domain.repository.TeamMemberRepository;
import com.modagbul.BE.domain.usermission.application.constant.Status;
import com.modagbul.BE.domain.usermission.domain.service.UserMissionSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;



@Service
@Transactional
@RequiredArgsConstructor
public class MissionCreateService {

    private final MissionQueryService missionQueryService;
    private final MissionSaveService missionSaveService;
    private final UserMissionSaveService userMissionSaveService;
    private final TeamQueryService teamQueryService;


    // 소모임장의 미션 생성
    public MissionRes createMission(Long teamId, MissionReq missionReq) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = dateFormat.format(new Date());

        if(missionReq.getDueTo().compareTo(formattedDate) < 0){
            throw new InvalidDueToDate();
        }


        if (missionQueryService.isLeader(teamId)){
            // 소모임장이라면 미션 생성
            Team findTeam = teamQueryService.getTeamById(teamId);

            Mission newMission = new Mission();

            newMission.createMission(findTeam, missionReq.getTitle(), missionReq.getDueTo(), missionReq.getContent(), missionReq.getRule());

            Mission savedMission = missionSaveService.saveMission(newMission);

            userMissionSaveService.makeUserMission(teamId, findTeam, savedMission);

            return new MissionRes(savedMission.getTitle(), savedMission.getDueTo(), savedMission.getContent(), savedMission.getRule(), Status.INCOMPLETE);

        }
        else{
            // 소모임장이 아니라면 예외 처리
            throw new MissionAuthDeniedException();
        }


    }



}
