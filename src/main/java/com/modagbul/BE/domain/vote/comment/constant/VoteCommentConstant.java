package com.modagbul.BE.domain.vote.comment.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class VoteCommentConstant {

    @Getter
    @RequiredArgsConstructor
    public enum EVoteCommentResponseMessage {
        CREATE_VOTE_COMMENT_SUCCESS("투표의 댓글을 생성하였습니다"),
        DELETE_VOTE_COMMENT_SUCCESS("투표의 댓글을 삭제하였습니다"),
        GET_VOTE_COMMENT_SUCCESS("투표 댓글 목록을 최신순으로 조회하였습니다");

        private final String message;
    }

    @Getter
    @RequiredArgsConstructor
    public enum VoteCommentExceptionList{
        NOT_FOUND_VOTE_COMMENT_ID_ERROR("VC0001", HttpStatus.NOT_FOUND, "해당 voteCommentId인 댓글이 존재하지 않습니다"),
        NOT_VOTE_COMMENT_WRITER_ERROR("VC0002", HttpStatus.NOT_FOUND, "해당 댓글을 작성한 유저가 아닙니다");
        private final String errorCode;
        private final HttpStatus httpStatus;
        private final String message;
    }

}
