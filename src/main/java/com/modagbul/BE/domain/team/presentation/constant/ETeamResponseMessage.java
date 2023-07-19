package com.modagbul.BE.domain.team.presentation.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

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