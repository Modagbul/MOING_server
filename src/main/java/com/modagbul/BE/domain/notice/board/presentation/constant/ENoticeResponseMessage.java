package com.modagbul.BE.domain.notice.board.presentation.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ENoticeResponseMessage {
    CREATE_NOTICE_SUCCESS("공지를 생성하였습니다"),
    DELETE_NOTICE_SUCCESS("공지를 삭제하였습니다"),
    GET_NOTICE_DETAIL_SUCCESS("공지를 상세 조회하였습니다"),
    GET_NOTICE_ALL_SUCCESS("공지를 전체 조회하였습니다"),
    GET_NOTICE_UNREAD_SUCCESS("확인하지 않은 공지를 최신순으로 조회하였습니다");
    private final String message;
}
