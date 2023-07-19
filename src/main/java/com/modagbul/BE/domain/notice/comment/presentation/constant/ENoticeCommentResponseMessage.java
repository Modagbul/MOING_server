package com.modagbul.BE.domain.notice.comment.presentation.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ENoticeCommentResponseMessage {
    CREATE_NOTICE_COMMENT_SUCCESS("공지의 댓글을 생성하였습니다"),
    DELETE_NOTICE_COMMENT_SUCCESS("공지의 댓글을 삭제하였습니다"),
    GET_NOTICE_COMMENT_SUCCESS("공지 댓글 목록을 최신순으로 조회하였습니다");

    private final String message;
}
