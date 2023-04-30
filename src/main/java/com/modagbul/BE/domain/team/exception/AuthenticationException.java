package com.modagbul.BE.domain.team.exception;

import com.modagbul.BE.domain.team.constant.TeamConstant;
import org.springframework.http.HttpStatus;

import static com.modagbul.BE.domain.team.constant.TeamConstant.TeamExceptionList.AUTHENTICATION_ERROR;

public class AuthenticationException extends TeamException{

    public AuthenticationException() {
        super(AUTHENTICATION_ERROR.getErrorCode(),
                AUTHENTICATION_ERROR.getHttpStatus(),
                AUTHENTICATION_ERROR.getMessage());
    }
}
