package com.modagbul.BE.domain.vote.comment.exception;

import static com.modagbul.BE.domain.vote.comment.constant.VoteCommentConstant.VoteCommentExceptionList.NOT_VOTE_COMMENT_WRITER_ERROR;

public class NotVoteCommentWriterException extends VoteCommentException {
    public NotVoteCommentWriterException(){
        super(NOT_VOTE_COMMENT_WRITER_ERROR.getErrorCode(),
                NOT_VOTE_COMMENT_WRITER_ERROR.getHttpStatus(),
                NOT_VOTE_COMMENT_WRITER_ERROR.getMessage());
    }
}
