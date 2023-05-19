package com.modagbul.BE.domain.team.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class TeamConstant {

    @Getter
    @RequiredArgsConstructor
    public enum ETeamResponseMessage{
        CREATE_TEAM_SUCCESS("소모임을 생성하였습니다"),
        JOIN_TEAM_SUCCESS("소모임 참여 코드 인증에 성공하였습니다"),
        GET_TEAM_INFO_SUCCESS("소모임 수정을 위한 정보를 조회하였습니다"),
        UPDATE_TEAM_SUCCESS("소모임을 수정하였습니다"),
        GET_TEAM_SUCCESS("모든 소모임을 조회하였습니다"),
        CHECK_TEAM_NAME_SUCCESS("소모임 이름 중복 검사를 하였습니다"),
        GET_GOAL_BOARD_PROFILE_SUCCESS("목표보드 프로필을 조회하였습니다"),
        GET_INVITE_CODE_SUCCESS("소모임 초대코드를 조회하였습니다");
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
        ALREADY_ERROR("T0002", HttpStatus.UNAUTHORIZED, "이미 해당 소모임에 참여했습니다"),
        NOT_HAVE_TEAMID_ERROR("T0003", HttpStatus.NOT_FOUND, "해당 TeamId인 소모임이 존재하지 않습니다"),
        ACCESS_ERROR("T0004", HttpStatus.UNAUTHORIZED, "소모임장이 아니어서 할 수 없습니다.");
        private final String errorCode;
        private final HttpStatus httpStatus;
        private final String message;
    }

    @Getter
    @RequiredArgsConstructor
    public enum TeamServiceMessage {
        VALID_TEAMNAME("가능한 소모임 이름입니다"),
        EXISTED_TEAMNAME("이미 존재하는 소모임 이름입니다");
        private final String value;
    }
}
