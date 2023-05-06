package com.modagbul.BE.domain.fire.exception;

import org.springframework.http.HttpStatus;

import static com.modagbul.BE.domain.fire.constant.FireExceptionList.FIRE_AUTH_DENIED;

public class FireAuthDeniedException extends FireException{
    public FireAuthDeniedException() {
        super(FIRE_AUTH_DENIED.getErrorCode(), FIRE_AUTH_DENIED.getHttpStatus(), FIRE_AUTH_DENIED.getMessage());
    }
}
