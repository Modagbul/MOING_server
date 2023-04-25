package com.modagbul.BE.global.config.security.exception;

import static com.modagbul.BE.global.config.security.constants.SecurityConstants.SecurityExceptionList.REQUIRED_LOGGED_IN;

public class RequiredLoggedInException extends SecurityException{
    public RequiredLoggedInException() {
        super(REQUIRED_LOGGED_IN.getErrorCode(),
                REQUIRED_LOGGED_IN.getHttpStatus(),
                REQUIRED_LOGGED_IN.getMessage()
        );
    }
}
