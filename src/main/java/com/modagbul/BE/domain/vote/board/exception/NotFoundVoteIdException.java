package com.modagbul.BE.domain.vote.board.exception;


import static com.modagbul.BE.domain.vote.board.exception.constant.VoteExceptionList.NOT_HAVE_VOTE_ID_ERROR;

public class NotFoundVoteIdException extends VoteException{
    public NotFoundVoteIdException(){
        super(NOT_HAVE_VOTE_ID_ERROR.getErrorCode(),
                NOT_HAVE_VOTE_ID_ERROR.getHttpStatus(),
                NOT_HAVE_VOTE_ID_ERROR.getMessage());
    }
}
