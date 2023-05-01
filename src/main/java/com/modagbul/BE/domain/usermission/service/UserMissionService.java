package com.modagbul.BE.domain.usermission.service;

import com.modagbul.BE.domain.mission.Exception.NotFoundMissionException;
import com.modagbul.BE.domain.mission.entity.Mission;
import com.modagbul.BE.domain.mission.repository.MissionRepository;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.team.repository.TeamRepository;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.domain.usermission.dto.UserMissionDetailDto;
import com.modagbul.BE.domain.usermission.dto.UserMissionListDto;
import com.modagbul.BE.domain.usermission.entity.UserMission;
import com.modagbul.BE.domain.usermission.exception.NotFoundUserMissionsException;
import com.modagbul.BE.domain.usermission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserMissionService {

    private final UserMissionRepository userMissionRepository;

    private final TeamRepository teamRepository;
    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    public List<UserMissionListDto> getUserMissionList(Long teamId){

        Long userId = 1L;
        return userMissionRepository.findUserMissionListById(teamId,userId).orElseThrow(NotFoundUserMissionsException::new);
    }

    public UserMissionDetailDto getUserMissionDetail(Long teamId, Long missionId) {
        Long userId = 1L;
        return userMissionRepository.findUserMissionDetailById(teamId,userId,missionId).orElseThrow(NotFoundMissionException::new);
    }




    public void submitUserMission(Long teamId, Long missionId, String submitUrl) {

        Long userId = 1L;

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("해당 유저를 찾을 수 없습니다."));
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new IllegalStateException("해당 팀을 찾을 수 없습니다."));
        Mission mission = missionRepository.findById(missionId).orElseThrow(NotFoundMissionException::new);

        UserMission userMission = new UserMission();
        userMission.createUserMission(user,team,mission);

        userMission.setComplete(submitUrl);

    }



}
