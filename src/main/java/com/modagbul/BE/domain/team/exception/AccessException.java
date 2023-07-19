package com.modagbul.BE.domain.team.exception;


import static com.modagbul.BE.domain.team.exception.constant.TeamExceptionList.ACCESS_ERROR;

public class AccessException extends TeamException{
    public AccessException(){
        super(ACCESS_ERROR.getErrorCode(),
                ACCESS_ERROR.getHttpStatus(),
                ACCESS_ERROR.getMessage());
    }
}
