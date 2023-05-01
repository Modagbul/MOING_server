package com.modagbul.BE.domain.usermission.service;

import com.modagbul.BE.domain.usermission.entity.UserMission;
import com.modagbul.BE.domain.usermission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMissionService {

    private final UserMissionRepository userMissionRepository;

    public void submitUserMission(Long teamId, Long missionId, String submitUrl) {
        UserMission userMission = new UserMission();
        userMission.setTeamId(teamId);

        userMission.setComplete();

    }



}
