package com.modagbul.BE.domain.mission.fcm.service;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.modagbul.BE.domain.mission.main.domain.entity.Mission;
import com.modagbul.BE.domain.mission.main.domain.repository.MissionRepository;
import com.modagbul.BE.domain.mission.main.domain.service.MissionQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MissionFcmScheduler {

    private final MissionFcmService missionFcmService;
    private final MissionRepository missionRepository;

    private final MissionQueryService missionQueryService;


//    @Scheduled(cron = "0/30 * * * * *") // test를 위해 30초에 한번씩 실행하도록 함.
    @Scheduled(cron = "0 0 6 * * *") // 매일 오전 6시에 이 메소드를 실행함.
    public void checkDailyOneDayBefore() throws FirebaseMessagingException {
        // 지금 시간 기준, 하루 전 23:59:59 ~ 00:00:01 가 포함된 미션들을 가져옴.

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneDayEnd = now.plusDays(1).withHour(23).withMinute(59).withSecond(59).withNano(0);
        LocalDateTime oneDayStart = now.plusDays(1).withHour(0).withMinute(0).withSecond(1).withNano(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String criteriaEnd = oneDayEnd.format(formatter);
        String criteriaStart = oneDayStart.format(formatter);

        List<Mission> missions = missionQueryService.getOneDayBeforeDueTo(criteriaStart, criteriaEnd);

        for (Mission mission : missions) {
            missionFcmService.setMission(mission);
            missionFcmService.pushBeforeOneDay();
        }

    }
    @Scheduled(cron = "0 0 12 * * *") // 매일 오후 12시에 이 메소드를 실행함.
    public void checkDailyDDayBefore() throws FirebaseMessagingException {
        // 지금 시간 기준, 당일 23:59:59 ~ 00:00:01 가 포함된 미션들을 가져옴.
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneDayEnd = now.plusDays(0).withHour(23).withMinute(59).withSecond(59).withNano(0);
        LocalDateTime oneDayStart = now.plusDays(0).withHour(0).withMinute(0).withSecond(1).withNano(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String criteriaEnd = oneDayEnd.format(formatter);
        String criteriaStart = oneDayStart.format(formatter);

        List<Mission> missions = missionQueryService.getOneDayBeforeDueTo(criteriaStart, criteriaEnd);
        for (Mission mission : missions) {
            missionFcmService.setMission(mission);
            missionFcmService.pushBeforeDDay();
        }

    }

}
