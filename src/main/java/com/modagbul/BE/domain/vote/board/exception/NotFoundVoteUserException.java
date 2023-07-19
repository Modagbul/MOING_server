package com.modagbul.BE.domain.vote.board.exception;

import static com.modagbul.BE.domain.vote.board.exception.constant.VoteExceptionList.NOT_HAVE_VOTE_BY_USER_ERROR;


public class NotFoundVoteUserException extends VoteException{
    public NotFoundVoteUserException(){
        super(NOT_HAVE_VOTE_BY_USER_ERROR.getErrorCode(),
                NOT_HAVE_VOTE_BY_USER_ERROR.getHttpStatus(),
                NOT_HAVE_VOTE_BY_USER_ERROR.getMessage());
    }
}
