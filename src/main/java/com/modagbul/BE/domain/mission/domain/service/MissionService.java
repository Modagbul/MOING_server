package com.modagbul.BE.domain.mission.domain.service;

import com.modagbul.BE.domain.mission.exception.InvalidDueToDate;
import com.modagbul.BE.domain.mission.exception.MissionAuthDeniedException;
import com.modagbul.BE.domain.mission.exception.NotFoundMissionException;
import com.modagbul.BE.domain.mission.application.dto.MissionListDto;

import com.modagbul.BE.domain.mission.domain.entity.Mission;
import com.modagbul.BE.domain.mission.domain.repository.MissionRepository;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.team.repository.TeamRepository;
import com.modagbul.BE.domain.team_member.repository.TeamMemberRepository;
import com.modagbul.BE.domain.usermission.constant.Status;
import com.modagbul.BE.domain.usermission.dto.UserMissionDetailDto;
import com.modagbul.BE.domain.usermission.entity.UserMission;
import com.modagbul.BE.domain.usermission.exception.NotFoundUserMissionsException;
import com.modagbul.BE.domain.usermission.repository.UserMissionRepository;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.modagbul.BE.domain.mission.application.dto.MissionDto.*;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;


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
            Mission savedMission = missionRepository.save(newMission);

            List<UserMission> userMissionList = new ArrayList<>();

            teamMemberRepository.findUserListByTeamId(teamId).orElseThrow(NotFoundUserMissionsException::new).forEach(user -> {
                userMissionList.add(new UserMission().createUserMission(user,findteam,savedMission));
            });

            userMissionRepository.saveAll(userMissionList);

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

        List<MissionListDto> incompleteMissions = missionRepository.findIncompleteMissionListById(teamId,userId,Status.INCOMPLETE).orElseThrow(NotFoundUserMissionsException::new);
        List<MissionListDto> completeMissions = missionRepository.findCompleteMissionListById(teamId, userId, Status.COMPLETE).orElseThrow(NotFoundUserMissionsException::new);

        List<MissionListDto> missionListDtos = new ArrayList<>();
        List<MissionListDto> pastMissions = new ArrayList<>();

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
        UserMissionDetailDto userMissionDetailDto = userMissionRepository.findUserMissionDetailById(teamId, userId, missionId).orElseThrow(NotFoundMissionException::new);
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
