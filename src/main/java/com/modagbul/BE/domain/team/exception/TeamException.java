package com.modagbul.BE.domain.team.exception;

import com.modagbul.BE.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class TeamException extends ApplicationException {
    protected TeamException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
