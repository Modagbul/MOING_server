package com.modagbul.BE.domain.vote.board.exception;

import com.modagbul.BE.domain.notice.board.exception.NotFoundNoticeUserException;
import com.modagbul.BE.domain.vote.board.constant.VoteConstant;

import static com.modagbul.BE.domain.vote.board.constant.VoteConstant.VoteExceptionList.NOT_HAVE_VOTE_BY_USER_ERROR;

public class NotFoundVoteUserException extends VoteException{
    public NotFoundVoteUserException(){
        super(NOT_HAVE_VOTE_BY_USER_ERROR.getErrorCode(),
                NOT_HAVE_VOTE_BY_USER_ERROR.getHttpStatus(),
                NOT_HAVE_VOTE_BY_USER_ERROR.getMessage());
    }
}
