package com.modagbul.BE.global.config.security.exception;

import com.modagbul.BE.global.config.security.constants.SecurityConstants;
import org.springframework.http.HttpStatus;

import static com.modagbul.BE.global.config.security.constants.SecurityConstants.SecurityExceptionList.LOGGED_IN_NOT_FOUND;

public class LoggedInUserNotFoundException extends SecurityException {
    public LoggedInUserNotFoundException() {
        super(LOGGED_IN_NOT_FOUND.getErrorCode(),
                LOGGED_IN_NOT_FOUND.getHttpStatus(),
                LOGGED_IN_NOT_FOUND.getMessage());
    }
}
