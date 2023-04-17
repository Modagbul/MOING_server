package com.modagbul.BE.global.config.security.exception;

import org.springframework.http.HttpStatus;

public class LoggedInUserNotFoundException extends SecurityException {
    public LoggedInUserNotFoundException() {
        super("S0003", HttpStatus.UNAUTHORIZED, "UNAUTHORIZED: 로그인한 사용자를 찾을 수 없습니다.");
    }
}
