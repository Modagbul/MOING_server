package com.modagbul.BE.domain.notice.comment.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class NoticeCommentConstant {

    @Getter
    @RequiredArgsConstructor
    public enum ENoticeCommentResponseMessage {
        CREATE_NOTICE_COMMENT_SUCCESS("공지의 댓글을 생성하였습니다"),
        DELETE_NOTICE_COMMENT_SUCCESS("공지의 댓글을 삭제하였습니다"),
        GET_NOTICE_COMMENT_SUCCESS("공지 댓글 목록을 최신순으로 조회하였습니다");

        private final String message;
    }

    @Getter
    @RequiredArgsConstructor
    public enum NoticeCommentExceptionList{
        NOT_FOUND_NOTICE_COMMENT_ID_ERROR("NC0001", HttpStatus.NOT_FOUND, "해당 noticeCommentId인 댓글이 존재하지 않습니다"),
        NOT_NOTICE_COMMENT_WRITER_ERROR("NC0002", HttpStatus.NOT_FOUND, "해당 댓글을 작성한 유저가 아닙니다");
        private final String errorCode;
        private final HttpStatus httpStatus;
        private final String message;
    }
}
