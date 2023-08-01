package com.modagbul.BE.domain.usermission.application.service;

import com.modagbul.BE.domain.fire.domain.service.FireQueryService;
import com.modagbul.BE.domain.mission.main.domain.service.MissionQueryService;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.domain.usermission.application.constant.Status;
import com.modagbul.BE.domain.usermission.domain.entity.UserMission;
import com.modagbul.BE.domain.usermission.domain.service.UserMissionQueryService;
import com.modagbul.BE.domain.usermission.domain.service.UserMissionSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserMissionCreateService {

    private final UserMissionQueryService userMissionQueryService;
    private final UserMissionSaveService userMissionSaveService;

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
}
