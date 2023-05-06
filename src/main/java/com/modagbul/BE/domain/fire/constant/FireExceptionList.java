package com.modagbul.BE.domain.fire.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FireExceptionList {

    FIRE_AUTH_DENIED("F0001", HttpStatus.UNAUTHORIZED, "인증 완료한 사용자에게 불던지기를 할 수 없습니다.");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;
}
