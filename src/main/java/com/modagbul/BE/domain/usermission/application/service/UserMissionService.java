package com.modagbul.BE.domain.usermission.application.service;

import com.modagbul.BE.domain.fire.domain.service.FireQueryService;
import com.modagbul.BE.domain.mission.main.domain.service.MissionQueryService;
import com.modagbul.BE.domain.mission.main.domain.entity.Mission;
import com.modagbul.BE.domain.team.domain.repository.TeamRepository;

import com.modagbul.BE.domain.team.domain.service.TeamQueryService;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.domain.usermission.application.constant.Status;
import com.modagbul.BE.domain.usermission.application.dto.UserMissionListDto;
import com.modagbul.BE.domain.usermission.application.dto.UserMissionStatusDto;
import com.modagbul.BE.domain.usermission.domain.entity.UserMission;
import com.modagbul.BE.domain.usermission.domain.service.UserMissionQueryService;
import com.modagbul.BE.domain.usermission.domain.service.UserMissionSaveService;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserMissionService {

    private final UserRepository userRepository;

    private final UserMissionQueryService userMissionQueryService;
    private final UserMissionSaveService userMissionSaveService;
    private final MissionQueryService missionQueryService;
    private final FireQueryService fireQueryService;


    public Status submitUserMission(Long teamId, Long missionId, String submitUrl) {

        UserMission userMission = userMissionQueryService.getUserMissionById(teamId, missionId);
        userMission.setComplete(submitUrl);

        return userMissionSaveService.saveUserMission(userMission).getStatus();

    }

    public Status skipUserMission(Long teamId, Long missionId, String skipReason) {

        UserMission userMission = userMissionQueryService.getUserMissionById(teamId, missionId);
        userMission.setPending(skipReason);

        return userMissionSaveService.saveUserMission(userMission).getStatus();

    }

    public UserMissionStatusDto getUserMissionList(Long teamId, Long missionId) {

        Long userId = SecurityUtils.getLoggedInUser().getUserId();
        User loginUser = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("해당 유저를 찾을 수 없습니다."));
        Mission mission = missionQueryService.getMissionById(missionId);

        String title = mission.getTitle();
        List<UserMissionListDto> completeList = userMissionQueryService.getCompleteUserMission(teamId, missionId, Status.COMPLETE);
        List<UserMissionListDto> incompleteList = userMissionQueryService.getInCompleteUserMission(teamId, missionId, Status.INCOMPLETE);
        Status mystatus = null;
        // my submit
        UserMissionListDto mine = null;

        // pending list append
        completeList.addAll(userMissionQueryService.getCompleteUserMission(teamId, missionId, Status.PENDING));


        Iterator<UserMissionListDto> iterator1 = completeList.iterator();
        while (iterator1.hasNext()) {
            UserMissionListDto next =iterator1.next();
            if (next.getNickname().equals(loginUser.getNickName())) {
                mine = next;
                iterator1.remove();
            }
        }

        if (mine != null) {
            completeList.add(0, mine);
            mystatus=mine.getStatus();
        }
        else{
            Iterator<UserMissionListDto> iterator2 = incompleteList.iterator();
            while (iterator2.hasNext()) {
                UserMissionListDto next = iterator2.next();
                if (next.getNickname().equals(loginUser.getNickName())) {
                    mine = next;
                    iterator2.remove();
                }
            }
            incompleteList.add(0, mine);
            mystatus=mine.getStatus();

        }

        UserMissionStatusDto userMissionStatusDto = new UserMissionStatusDto(title, completeList, incompleteList, mystatus);

        userMissionStatusDto.setFireUserMissionList(fireQueryService.getFireById(userId,missionId));

        // complete/incomplete num
        int completeSize = completeList.size();
        int incompleteSize = userMissionStatusDto.getIncompleteList().size();
        userMissionStatusDto.setUserNum(completeSize,incompleteSize);
        userMissionStatusDto.setRemainDay(getRemainPeriod(mission.getDueTo()));

        return userMissionStatusDto;

    }
    // remain peroiod calculate
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




}
