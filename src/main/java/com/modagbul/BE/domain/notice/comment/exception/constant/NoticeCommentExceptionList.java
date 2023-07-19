package com.modagbul.BE.domain.notice.comment.exception.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum NoticeCommentExceptionList{
    NOT_FOUND_NOTICE_COMMENT_ID_ERROR("NC0001", HttpStatus.NOT_FOUND, "해당 noticeCommentId인 댓글이 존재하지 않습니다"),
    NOT_NOTICE_COMMENT_WRITER_ERROR("NC0002", HttpStatus.NOT_FOUND, "해당 댓글을 작성한 유저가 아닙니다");
    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;
}
