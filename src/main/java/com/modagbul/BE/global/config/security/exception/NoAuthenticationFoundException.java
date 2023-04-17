package com.modagbul.BE.global.config.security.exception;

import com.modagbul.BE.global.config.security.constants.SecurityConstants;
import org.springframework.http.HttpStatus;

import static com.modagbul.BE.global.config.security.constants.SecurityConstants.SecurityExceptionList.NO_AUTHENTICATION_FOUND;

public class NoAuthenticationFoundException extends SecurityException {
    public NoAuthenticationFoundException() {
        super(NO_AUTHENTICATION_FOUND.getErrorCode(),
                NO_AUTHENTICATION_FOUND.getHttpStatus(),
                NO_AUTHENTICATION_FOUND.getMessage() );
    }
}
