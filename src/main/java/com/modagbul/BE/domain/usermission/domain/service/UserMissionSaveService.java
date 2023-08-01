package com.modagbul.BE.domain.usermission.domain.service;

import com.modagbul.BE.domain.mission.main.domain.entity.Mission;
import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.domain.team_member.domain.repository.TeamMemberRepository;
import com.modagbul.BE.domain.usermission.domain.entity.UserMission;
import com.modagbul.BE.domain.usermission.domain.repository.UserMissionRepository;
import com.modagbul.BE.domain.usermission.exception.NotFoundUserMissionsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UserMissionSaveService {

    private final TeamMemberRepository teamMemberRepository;
    private final UserMissionRepository userMissionRepository;

    public void makeUserMission(Long teamId, Team findTeam, Mission savedMission) {
        List<UserMission> userMissionList = new ArrayList<>();

        teamMemberRepository.findUserListByTeamId(teamId).orElseThrow(NotFoundUserMissionsException::new).forEach(user -> {
            userMissionList.add(new UserMission().createUserMission(user,findTeam,savedMission));
        });

        userMissionRepository.saveAll(userMissionList);
    }

    public UserMission saveUserMission(UserMission userMission) {
        return userMissionRepository.save(userMission);
    }
}
