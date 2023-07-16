package com.modagbul.BE.domain.fire.exception;

import static com.modagbul.BE.domain.fire.application.constant.FireExceptionList.FIRE_AUTH_DENIED;

public class FireAuthDeniedException extends FireException{
    public FireAuthDeniedException() {
        super(FIRE_AUTH_DENIED.getErrorCode(), FIRE_AUTH_DENIED.getHttpStatus(), FIRE_AUTH_DENIED.getMessage());
    }
}
