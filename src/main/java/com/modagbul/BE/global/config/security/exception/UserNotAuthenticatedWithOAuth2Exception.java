package com.modagbul.BE.global.config.security.exception;

import static com.modagbul.BE.global.config.security.constants.SecurityConstants.SecurityExceptionList.NOT_AUTH_WITH_OAUTH2;

public class UserNotAuthenticatedWithOAuth2Exception extends SecurityException {
    public UserNotAuthenticatedWithOAuth2Exception() {
        super(NOT_AUTH_WITH_OAUTH2.getErrorCode(),
                NOT_AUTH_WITH_OAUTH2.getHttpStatus(),
                NOT_AUTH_WITH_OAUTH2.getMessage());
    }
}
