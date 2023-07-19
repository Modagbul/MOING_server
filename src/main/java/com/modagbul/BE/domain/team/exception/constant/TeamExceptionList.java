package com.modagbul.BE.domain.team.exception.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TeamExceptionList{
    AUTHENTICATION_ERROR("T0001", HttpStatus.UNAUTHORIZED, "참여코드 값이 잘못되었습니다"),
    ALREADY_ERROR("T0002", HttpStatus.UNAUTHORIZED, "이미 해당 소모임에 참여했습니다"),
    NOT_HAVE_TEAMID_ERROR("T0003", HttpStatus.NOT_FOUND, "해당 TeamId인 소모임이 존재하지 않습니다"),
    ACCESS_ERROR("T0004", HttpStatus.UNAUTHORIZED, "소모임장이 아니어서 할 수 없습니다.");
    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;
}
