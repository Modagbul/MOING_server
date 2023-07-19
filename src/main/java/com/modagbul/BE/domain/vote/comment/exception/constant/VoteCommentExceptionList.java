package com.modagbul.BE.domain.vote.comment.exception.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum VoteCommentExceptionList {
    NOT_FOUND_VOTE_COMMENT_ID_ERROR("VC0001", HttpStatus.NOT_FOUND, "해당 voteCommentId인 댓글이 존재하지 않습니다"),
    NOT_VOTE_COMMENT_WRITER_ERROR("VC0002", HttpStatus.NOT_FOUND, "해당 댓글을 작성한 유저가 아닙니다");
    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;
}
