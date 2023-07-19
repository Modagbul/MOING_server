package com.modagbul.BE.domain.vote.comment.presentation.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EVoteCommentResponseMessage {
    CREATE_VOTE_COMMENT_SUCCESS("투표의 댓글을 생성하였습니다"),
    DELETE_VOTE_COMMENT_SUCCESS("투표의 댓글을 삭제하였습니다"),
    GET_VOTE_COMMENT_SUCCESS("투표 댓글 목록을 최신순으로 조회하였습니다");

    private final String message;
}