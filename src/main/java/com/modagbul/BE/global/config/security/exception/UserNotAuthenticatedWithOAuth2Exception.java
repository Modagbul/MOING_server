package com.modagbul.BE.global.config.security.exception;

import org.springframework.http.HttpStatus;

public class UserNotAuthenticatedWithOAuth2Exception extends SecurityException {
    public UserNotAuthenticatedWithOAuth2Exception() {
        super("S0002", HttpStatus.UNAUTHORIZED, "UNAUTHORIZED : 현재 사용자는 OAuth2를 사용하여 인증되지 않았습니다.");
    }
}
