package com.modagbul.BE.domain.mission.application.service;

import com.modagbul.BE.domain.mission.domain.entity.Mission;
import com.modagbul.BE.domain.mission.domain.repository.MissionRepository;
import com.modagbul.BE.domain.mission.domain.service.MissionQueryService;
import com.modagbul.BE.domain.mission.exception.InvalidDueToDate;
import com.modagbul.BE.domain.mission.exception.MissionAuthDeniedException;
import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.domain.team.domain.repository.TeamRepository;
import com.modagbul.BE.domain.team_member.domain.repository.TeamMemberRepository;
import com.modagbul.BE.domain.usermission.application.constant.Status;
import com.modagbul.BE.domain.usermission.domain.repository.UserMissionRepository;
import com.modagbul.BE.domain.usermission.domain.service.UserMissionSaveService;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.modagbul.BE.domain.mission.application.dto.MissionDto.MissionReq;
import static com.modagbul.BE.domain.mission.application.dto.MissionDto.MissionRes;

@Service
@RequiredArgsConstructor
public class MissionSaveService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    private final MissionQueryService missionQueryService;
    private final com.modagbul.BE.domain.mission.domain.service.MissionSaveService missionSaveService;
    private final UserMissionSaveService userMissionSaveService;


    // 소모임장의 미션 생성
    public MissionRes createMission(Long teamId, MissionReq missionReq) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = dateFormat.format(new Date());

        if(missionReq.getDueTo().compareTo(formattedDate) < 0){
            throw new InvalidDueToDate();
        }
        // 날짜를 원하는 형식으로 포맷팅


        //  로그인한 사용자의 id
        Long loginId = SecurityUtils.getLoggedInUser().getUserId();

        // 로그인한 사용자가 소모임장인지 확인 -> 팀 leaderid 확인
        Team findteam = teamRepository.findById(teamId).orElseThrow(() -> new IllegalStateException("해당 팀을 찾을 수 없습니다."));

        if ( findteam.getLeaderId().equals(loginId)){
            // 소모임장이라면 미션 생성
            Mission newMission = new Mission();
            newMission.createMission(findteam, missionReq.getTitle(), missionReq.getDueTo(), missionReq.getContent(), missionReq.getRule());
            Mission savedMission = missionSaveService.saveMission(newMission);

            userMissionSaveService.makeUserMission(teamId, findteam, savedMission);

            return new MissionRes(savedMission.getTitle(), savedMission.getDueTo(), savedMission.getContent(), savedMission.getRule(), Status.INCOMPLETE);

        }
        else{
            // 소모임장이 아니라면 예외 처리
            throw new MissionAuthDeniedException();
        }


    }



}
