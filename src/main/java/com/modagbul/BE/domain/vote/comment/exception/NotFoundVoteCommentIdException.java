package com.modagbul.BE.domain.vote.comment.exception;

import static com.modagbul.BE.domain.vote.comment.constant.VoteCommentConstant.VoteCommentExceptionList.NOT_FOUND_VOTE_COMMENT_ID_ERROR;

public class NotFoundVoteCommentIdException extends VoteCommentException{
    public NotFoundVoteCommentIdException(){
        super(NOT_FOUND_VOTE_COMMENT_ID_ERROR.getErrorCode(),
                NOT_FOUND_VOTE_COMMENT_ID_ERROR.getHttpStatus(),
                NOT_FOUND_VOTE_COMMENT_ID_ERROR.getMessage());
    }
}
