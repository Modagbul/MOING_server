package com.modagbul.BE.domain.notice.board.exception.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

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