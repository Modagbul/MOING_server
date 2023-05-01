package com.modagbul.BE.domain.usermission.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserMissionResponseMessage {


    GET_MISSION_LIST_SUCCESS("개인별 미션 리스트 조회 성공"),
    GET_MISSION_DETAIL_SUCCESS("개인별 미션 상세 조회 성공");

    private final String message;

}
