package com.modagbul.BE.domain.team.exception;

import com.modagbul.BE.domain.team.constant.TeamConstant;

import static com.modagbul.BE.domain.team.constant.TeamConstant.TeamExceptionList.ALREADY_ERROR;

public class AlreadyJoinException extends TeamException{
    public AlreadyJoinException(){
        super(ALREADY_ERROR.getErrorCode(),
                ALREADY_ERROR.getHttpStatus(),
                ALREADY_ERROR.getMessage());
    }
}
