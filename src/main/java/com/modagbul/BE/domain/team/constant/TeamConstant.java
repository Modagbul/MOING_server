package com.modagbul.BE.domain.team.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class TeamConstant {

    @Getter
    @RequiredArgsConstructor
    public enum ETeamResponseMessage{
        CREATE_TEAM_SUCCESS("소모임을 생성하였습니다"),
        JOIN_TEAM_SUCCESS("소모임 참여 코드 인증에 성공하였습니다");
        private final String message;
    }
    @Getter
    @RequiredArgsConstructor
    public enum Category{
        SPORTS("스포츠/운동"),
        HABIT("생활습관 개선"),
        TEST("시험/취업준비"),
        STUDY("스터디/공부"),
        READING("독서"),
        ETC("그외 자기계발");

        private final String value;
        public String getValue(){
            return value;
        }
    }

    @Getter
    @RequiredArgsConstructor
    public enum TeamExceptionList{
        AUTHENTICATION_ERROR("T0001", HttpStatus.UNAUTHORIZED, "참여코드 값이 잘못되었습니다"),
        ALREADY_ERROR("T0002", HttpStatus.UNAUTHORIZED, "이미 해당 소모임에 참여했습니다");
        private final String errorCode;
        private final HttpStatus httpStatus;
        private final String message;
    }
}
