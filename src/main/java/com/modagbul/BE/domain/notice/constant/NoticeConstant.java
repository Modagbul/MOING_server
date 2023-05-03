package com.modagbul.BE.domain.notice.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class NoticeConstant {
    @Getter
    @RequiredArgsConstructor
    public enum ENoticeResponseMessage {
        CREATE_NOTICE_SUCCESS("공지를 생성하였습니다"),
        DELETE_NOTICE_SUCCESS("공지를 삭제하였습니다"),
        GET_NOTICE_DETAIL_SUCCESS("공지를 상세 조회하였습니다");

        private final String message;
    }

    @Getter
    @RequiredArgsConstructor
    public enum NoticeExceptionList {
        NOT_HAVE_NOTICEID_ERROR("N0001", HttpStatus.NOT_FOUND, "해당 noticeId인 공지가 존재하지 않습니다"),
        NOT_HAVE_NOTICE_BY_USER_ERROR("N0002", HttpStatus.NOT_FOUND, "해당 유저의 공지가 존재하지 않습니다"),
        NOT_NOTICE_WRITER_ERROR("N0003", HttpStatus.NOT_FOUND, "해당 공지를 작성한 유저가 아닙니다");
        private final String errorCode;
        private final HttpStatus httpStatus;
        private final String message;
    }
}
