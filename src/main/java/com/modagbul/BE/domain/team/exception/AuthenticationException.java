package com.modagbul.BE.domain.team.exception;

import static com.modagbul.BE.domain.team.exception.constant.TeamExceptionList.AUTHENTICATION_ERROR;


public class AuthenticationException extends TeamException{

    public AuthenticationException() {
        super(AUTHENTICATION_ERROR.getErrorCode(),
                AUTHENTICATION_ERROR.getHttpStatus(),
                AUTHENTICATION_ERROR.getMessage());
    }
}
