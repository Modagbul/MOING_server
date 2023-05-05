package com.modagbul.BE.domain.fire.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FireResponseMessage {

    THROW_FIRE_SUCCESS("불 던지기 성공"),
    CANCEL_FIRE_SUCCESS("불 던지기 성공");

    private final String message;

}
