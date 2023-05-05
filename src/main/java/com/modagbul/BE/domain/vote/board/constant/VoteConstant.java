package com.modagbul.BE.domain.vote.board.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class VoteConstant {
    @Getter
    @RequiredArgsConstructor
    public enum EVoteResponseMessage {
        CREATE_VOTE_SUCCESS("투표를 생성하였습니다"),
        DELETE_VOTE_SUCCESS("투표를 삭제하였습니다"),
        GET_VOTE_DETAIL_SUCCESS("투표를 상세 조회하였습니다");

        private final String message;
    }

    @Getter
    @RequiredArgsConstructor
    public enum VoteExceptionList {
        NOT_HAVE_VOTE_ID_ERROR("V0001", HttpStatus.NOT_FOUND, "해당 voteId인 투표가 존재하지 않습니다"),
        NOT_HAVE_VOTE_BY_USER_ERROR("V0002", HttpStatus.NOT_FOUND, "해당 유저의 투표가 존재하지 않습니다"),
        NOT_VOTE_WRITER_ERROR("V0003", HttpStatus.NOT_FOUND, "해당 투표를 작성한 유저가 아닙니다");
        private final String errorCode;
        private final HttpStatus httpStatus;
        private final String message;
    }
}