package com.modagbul.BE.domain.mission.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.modagbul.BE.domain.mission.Exception.NotFoundMissionException;
import com.modagbul.BE.domain.mission.constant.MissionFcmMessage;
import com.modagbul.BE.domain.mission.entity.Mission;
import com.modagbul.BE.domain.mission.repository.MissionRepository;
import com.modagbul.BE.fcm.dto.FcmDto;
import com.modagbul.BE.fcm.service.FcmService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class MissionFcmService {
    //mission의 dueTo가 하루전이면 알림을 보내준다.
    //mission의 dueTo가 1시간전이면 알림을 보내준다.

    private final FcmService fcmService;
    private final MissionRepository missionRepository;

    Mission mission = missionRepository.findById(1L).orElseThrow(NotFoundMissionException::new);

    MissionFcmMessage message = new MissionFcmMessage(mission);


    public void pushBeforeOneDay() throws FirebaseMessagingException {

        Random rand = new Random();
        message.init();
        message.getTeamMembers().forEach(member -> {
            List<String> strings = message.messageInitOneDay(member.getNickName(), mission.getTitle()).get(rand.nextInt(2));
            FcmDto.ToSingleRequest toSingleRequest = new FcmDto.ToSingleRequest(
                    strings.get(0),strings.get(1),member.getFcmToken()
            );
            fcmService.sendSingleDevice(toSingleRequest);

        });

    }
    public void pushBeforeDDay() throws FirebaseMessagingException {

        Random rand = new Random();
        message.init();
        message.getTeamMembers().forEach(member -> {
            List<String> strings = message.messageInitDDay(member.getNickName(), mission.getTitle()).get(rand.nextInt(3));
            FcmDto.ToSingleRequest toSingleRequest = new FcmDto.ToSingleRequest(
                    strings.get(0),strings.get(1),member.getFcmToken()
            );
            fcmService.sendSingleDevice(toSingleRequest);

        });

    }

}