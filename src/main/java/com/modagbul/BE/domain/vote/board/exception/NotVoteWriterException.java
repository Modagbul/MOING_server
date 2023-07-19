package com.modagbul.BE.domain.vote.board.exception;


import static com.modagbul.BE.domain.vote.board.exception.constant.VoteExceptionList.NOT_VOTE_WRITER_ERROR;

public class NotVoteWriterException extends VoteException{
    public NotVoteWriterException(){
        super(NOT_VOTE_WRITER_ERROR.getErrorCode(),
                NOT_VOTE_WRITER_ERROR.getHttpStatus(),
                NOT_VOTE_WRITER_ERROR.getMessage());
    }
}
