package com.modagbul.BE.domain.usermission.domain.service;

import com.modagbul.BE.domain.mission.domain.entity.Mission;
import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.domain.team_member.domain.repository.TeamMemberRepository;
import com.modagbul.BE.domain.usermission.domain.entity.UserMission;
import com.modagbul.BE.domain.usermission.domain.repository.UserMissionRepository;
import com.modagbul.BE.domain.usermission.exception.NotFoundUserMissionsException;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class UserMissionSaveService {

    private final TeamMemberRepository teamMemberRepository;
    private final UserMissionRepository userMissionRepository;

    public List<UserMission> makeUserMission(Long teamId, Team findTeam, Mission savedMission) {
        List<UserMission> userMissionList = new ArrayList<>();

        teamMemberRepository.findUserListByTeamId(teamId).orElseThrow(NotFoundUserMissionsException::new).forEach(user -> {
            userMissionList.add(new UserMission().createUserMission(user,findTeam,savedMission));
        });

        return userMissionRepository.saveAll(userMissionList);

    }
}