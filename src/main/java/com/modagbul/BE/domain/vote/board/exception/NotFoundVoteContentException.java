package com.modagbul.BE.domain.vote.board.exception;

import static com.modagbul.BE.domain.vote.board.constant.VoteConstant.VoteExceptionList.NOT_HAVE_VOTE_CONTENT_ERROR;

public class NotFoundVoteContentException extends VoteException{
    public NotFoundVoteContentException(){
        super(NOT_HAVE_VOTE_CONTENT_ERROR.getErrorCode(),
                NOT_HAVE_VOTE_CONTENT_ERROR.getHttpStatus(),
                NOT_HAVE_VOTE_CONTENT_ERROR.getMessage());
    }
}
