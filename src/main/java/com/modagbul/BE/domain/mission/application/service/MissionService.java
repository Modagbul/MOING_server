package com.modagbul.BE.domain.mission.application.service;

import com.modagbul.BE.domain.mission.domain.service.MissionQueryService;
import com.modagbul.BE.domain.mission.domain.service.MissionSaveService;
import com.modagbul.BE.domain.mission.application.dto.MissionListDto;

import com.modagbul.BE.domain.mission.domain.entity.Mission;
import com.modagbul.BE.domain.mission.domain.repository.MissionRepository;
import com.modagbul.BE.domain.team.domain.repository.TeamRepository;
import com.modagbul.BE.domain.team_member.domain.repository.TeamMemberRepository;
import com.modagbul.BE.domain.usermission.application.constant.Status;

import com.modagbul.BE.domain.usermission.application.dto.UserMissionDetailDto;
import com.modagbul.BE.domain.usermission.domain.service.UserMissionQueryService;
import com.modagbul.BE.domain.usermission.domain.service.UserMissionSaveService;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


@Transactional
@Service
@RequiredArgsConstructor
public class MissionService {

    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    private final MissionQueryService missionQueryService;
    private final MissionSaveService missionSaveService;
    private final UserMissionSaveService userMissionSaveService;
    private final UserMissionQueryService userMissionQueryService;


    // 개인별 미션 리스트 조회
    public List<MissionListDto> getMissionList(Long teamId){

        Long userId = SecurityUtils.getLoggedInUser().getUserId();

        List<MissionListDto> missionListDtos = new ArrayList<>();
        List<MissionListDto> pastMissions = new ArrayList<>();

        List<MissionListDto> incompleteMissions = missionQueryService.getIncompleteMissionListById(teamId,userId);
        List<MissionListDto> completeMissions = missionQueryService.getCompleteMissionListById(teamId,userId);


        missionListDtos.addAll(incompleteMissions);
        missionListDtos.addAll(completeMissions);


        Iterator<MissionListDto> iterator = missionListDtos.iterator();
        while (iterator.hasNext()) {
            MissionListDto next = iterator.next();
            if(!getDueToOK(next.getDueTo())){
                pastMissions.add(next);
                iterator.remove();
            }
            else{
//                next.setDueTo(getRemainPeriod(next.getDueTo()));
            }
        }
        //pastMission의 dueTo를 이용한 정렬을 통해 종료된 미션 순서 정렬
        pastMissions.sort((o1, o2) -> {
            try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date o1Date = format.parse(o1.getDueTo());
                Date o2Date = format.parse(o2.getDueTo());
                return o2Date.compareTo(o1Date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return 0;
        });

        missionListDtos.addAll(pastMissions);

        for (MissionListDto missionListDto : missionListDtos) {
            missionListDto.setDueTo(getRemainPeriod(missionListDto.getDueTo()));
        }


        return missionListDtos;

    }

    // 개인별 미션 상세 페이지 조회
    public UserMissionDetailDto getMissionDetail(Long teamId, Long missionId) {
        Long userId = SecurityUtils.getLoggedInUser().getUserId();

        UserMissionDetailDto userMissionDetailDto = userMissionQueryService.getUserMissionDetailById(teamId, userId, missionId);
        String remainPeriod = getRemainPeriod(userMissionDetailDto.getDueTo());
        userMissionDetailDto.setDueTo(remainPeriod);
        return userMissionDetailDto;
    }

    public boolean getDueToOK(String dueTo) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date dueToDate = format.parse(dueTo);
            Date now = new Date();

            if (dueToDate.getTime() - now.getTime() > 0) {
                System.out.println("미션 기간이 남았습니다.");
                return true;
            }else {
                System.out.println("미션 기간이 지났습니다.");
                return false;
            }

        } catch(ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    public String getRemainPeriod(String dueTo) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date dueToDate = format.parse(dueTo);
            Date now = new Date();

            long diffInMillis = dueToDate.getTime() - now.getTime();
            long diffInSeconds = diffInMillis / 1000;
            long diffInMinutes = diffInSeconds / 60;
            long diffInHours = diffInMinutes / 60;
            long diffInDays = diffInHours / 24;

            return Long.toString(diffInDays);

        } catch(ParseException e) {
            e.printStackTrace();
        }
        return null;
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
