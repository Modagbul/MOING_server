package com.modagbul.BE.domain.mission.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.modagbul.BE.domain.mission.Exception.NotFoundMissionException;
import com.modagbul.BE.domain.mission.constant.MissionFcmMessage;
import com.modagbul.BE.domain.mission.entity.Mission;
import com.modagbul.BE.domain.mission.repository.MissionRepository;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.usermission.repository.UserMissionRepository;
import com.modagbul.BE.fcm.dto.FcmDto;
import com.modagbul.BE.fcm.service.FcmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ScheduledFuture;

import static com.modagbul.BE.fcm.dto.FcmDto.*;

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