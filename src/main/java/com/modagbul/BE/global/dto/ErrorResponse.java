package com.modagbul.BE.global.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {

    private LocalDateTime timeStamp;
    private String errorCode;
    private String message;

    public ErrorResponse(String errorCode, String message) {
        this.timeStamp = LocalDateTime.now().withNano(0);
        this.errorCode = errorCode;
        this.message = message;
    }
}
