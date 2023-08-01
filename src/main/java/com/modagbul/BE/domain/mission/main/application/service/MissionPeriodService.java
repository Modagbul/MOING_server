package com.modagbul.BE.domain.mission.main.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Transactional
@Service
@RequiredArgsConstructor
public class MissionPeriodService {

    public boolean getDueToOK(String dueTo) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date dueToDate = format.parse(dueTo);
            Date now = new Date();

            if (dueToDate.getTime() - now.getTime() > 0) {
                System.out.println("미션 기간이 남았습니다.");
                return true;
            }else {
                System.out.println("미션 기간이 지났습니다.");
                return false;
            }

        } catch(ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


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
