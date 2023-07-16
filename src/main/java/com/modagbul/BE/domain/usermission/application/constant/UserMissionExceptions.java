package com.modagbul.BE.domain.usermission.application.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@RequiredArgsConstructor
public enum UserMissionExceptions {

        NOT_FOUND_USERMISSIONS("UM0001", HttpStatus.NOT_FOUND, "해당 유저의 미션을 불러올 수 없습니다.");
        private final String errorCode;
        private final HttpStatus httpStatus;
        private final String message;

}
