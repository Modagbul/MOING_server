package com.modagbul.BE.domain.usermission.service;

import com.modagbul.BE.domain.mission.Exception.NotFoundMissionException;
import com.modagbul.BE.domain.mission.entity.Mission;
import com.modagbul.BE.domain.mission.repository.MissionRepository;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.team.exception.NotFoundTeamIdException;
import com.modagbul.BE.domain.team.repository.TeamRepository;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.domain.usermission.constant.Status;
import com.modagbul.BE.domain.usermission.dto.UserMissionDetailDto;
import com.modagbul.BE.domain.usermission.dto.UserMissionListDto;
import com.modagbul.BE.domain.usermission.dto.UserMissionStatusDto;
import com.modagbul.BE.domain.usermission.entity.UserMission;
import com.modagbul.BE.domain.usermission.exception.NotFoundUserMissionsException;
import com.modagbul.BE.domain.usermission.repository.UserMissionRepository;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMissionService {

    private final UserMissionRepository userMissionRepository;
    private final TeamRepository teamRepository;
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;


    public Status submitUserMission(Long teamId, Long missionId, String submitUrl) {

        Long userId = SecurityUtils.getLoggedInUser().getUserId();

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("해당 유저를 찾을 수 없습니다."));
        Team team = teamRepository.findById(teamId).orElseThrow(NotFoundTeamIdException::new);
        Mission mission = missionRepository.findById(missionId).orElseThrow(NotFoundMissionException::new);

        UserMission userMission = userMissionRepository.findUserMissionById(userId, teamId, missionId).orElseThrow(NotFoundUserMissionsException::new);

        userMission.setPending(submitUrl);
        userMissionRepository.save(userMission);

        return userMission.getStatus();

    }

    public Status skipUserMission(Long teamId, Long missionId, String skipReason) {

        Long userId = SecurityUtils.getLoggedInUser().getUserId();

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("해당 유저를 찾을 수 없습니다."));
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalStateException("해당 팀을 찾을 수 없습니다."));
        Mission mission = missionRepository.findById(missionId).orElseThrow(NotFoundMissionException::new);

        UserMission userMission = userMissionRepository.findUserMissionById(userId, teamId, missionId).orElseThrow(NotFoundUserMissionsException::new);

        userMission.setPending(skipReason);
        userMissionRepository.save(userMission);

        return userMission.getStatus();

    }

    public UserMissionStatusDto getUserMissionList(Long teamId, Long missionId) {

        Long userId = SecurityUtils.getLoggedInUser().getUserId();
        Mission mission = missionRepository.findById(missionId).orElseThrow(NotFoundMissionException::new);

        UserMissionStatusDto userMissionStatusDto = new UserMissionStatusDto(
                mission.getTitle(),
                userMissionRepository.findCompleteUserMissionListById(teamId, missionId, Status.COMPLETE).orElseThrow(NotFoundUserMissionsException::new),
                userMissionRepository.findInCompleteUserMissionListById(teamId, missionId, Status.INCOMPLETE).orElseThrow(NotFoundUserMissionsException::new)

        );
        // pending list append
        userMissionStatusDto.getCompleteList().addAll(userMissionRepository.findCompleteUserMissionListById(teamId, missionId, Status.PENDING).orElseThrow(NotFoundUserMissionsException::new));
        // complete/incomplete num
        int completeSize = userMissionStatusDto.getCompleteList().size();
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
