package com.modagbul.BE.domain.vote.board.presentation.content;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EVoteResponseMessage {
    CREATE_VOTE_SUCCESS("투표를 생성하였습니다"),
    DO_VOTE_SUCCESS("투표를 하였습니다"),
    CLOSE_VOTE_SUCCESS("투표를 종료하였습니다"),
    GET_VOTE_DETAIL_SUCCESS("투표를 상세 조회하였습니다"),
    GET_VOTE_ALL_SUCCESS("투표를 전체 조회하였습니다"),
    GET_VOTE_UNREAD_SUCCESS("확인하지 않은 투표를  최신순으로 조회하였습니다");

    private final String message;
}