package com.modagbul.BE.domain.mission.domain.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.modagbul.BE.domain.mission.exception.NotFoundMissionException;
import com.modagbul.BE.domain.mission.application.constant.MissionFcmMessage;
import com.modagbul.BE.domain.mission.domain.entity.Mission;
import com.modagbul.BE.domain.mission.domain.repository.MissionRepository;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.usermission.repository.UserMissionRepository;
import com.modagbul.BE.global.config.fcm.dto.FcmDto.ToSingleRequest;
import com.modagbul.BE.global.config.fcm.service.FcmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.*;


@Slf4j

@Service
@RequiredArgsConstructor
public class MissionFcmService {

    private final FcmService fcmService;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private Mission mission;

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public void pushBeforeOneDay() throws FirebaseMessagingException {


        MissionFcmMessage message = new MissionFcmMessage(mission);

        // 하루 전에 완료 하지 않은 인원
        List<User> users = userMissionRepository.getInCompleteUsersByMission(mission).orElseThrow(NotFoundMissionException::new);

        // 개인별 메시지 생성
        message.init(users);

        // 개인별 메시지 전송 (현재 user에 fcm token 없어서 sendSingleDevice 안됨
        message.getNotCompleteMembers().forEach(member -> {
            log.info("pushBeforeOneDay" + member.getNickName() + " " + mission.getTitle());

            List<String> strings = message.messageInitOneDay(member.getNickName(), mission.getTitle()).get((int) (Math.random() * 3));
            ToSingleRequest toSingleRequest = new ToSingleRequest(
                    strings.get(0), strings.get(1), member.getFcmToken()
            );
            System.out.println(strings.get(0) + " " + strings.get(1) + " " + member.getFcmToken());
//                fcmService.sendSingleDevice(toSingleRequest);
        });

    }
    public void pushBeforeDDay() throws FirebaseMessagingException {


        MissionFcmMessage message = new MissionFcmMessage(mission);

        // 하루 전에 완료 하지 않은 인원
        List<User> users = userMissionRepository.getInCompleteUsersByMission(mission).orElseThrow(NotFoundMissionException::new);

        // 개인별 메시지 생성
        message.init(users);

        // 개인별 메시지 전송 (현재 user에 fcm token 없어서 sendSingleDevice 안됨
        message.getNotCompleteMembers().forEach(member -> {
            log.info("pushBeforeOneDay" + member.getNickName() + " " + mission.getTitle());

            List<String> strings = message.messageInitDDay(member.getNickName(), mission.getTitle()).get((int) (Math.random() * 4));
            ToSingleRequest toSingleRequest = new ToSingleRequest(
                    strings.get(0), strings.get(1), member.getFcmToken()
            );
            System.out.println(strings.get(0) + " " + strings.get(1) + " " + member.getFcmToken());
//                fcmService.sendSingleDevice(toSingleRequest);
        });

    }
}
//