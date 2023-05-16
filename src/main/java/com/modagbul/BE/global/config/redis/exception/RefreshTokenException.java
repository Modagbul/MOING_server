package com.modagbul.BE.global.config.redis.exception;

import com.modagbul.BE.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class RefreshTokenException extends ApplicationException {
    protected RefreshTokenException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
