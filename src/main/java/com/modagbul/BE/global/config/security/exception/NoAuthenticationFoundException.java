package com.modagbul.BE.global.config.security.exception;

import org.springframework.http.HttpStatus;

public class NoAuthenticationFoundException extends SecurityException {
    public NoAuthenticationFoundException() {
        super("S0001", HttpStatus.UNAUTHORIZED, "UNAUTHORIZED: 보안 컨텍스트에서 인증 정보를 찾을 수 없습니다.");
    }
}
