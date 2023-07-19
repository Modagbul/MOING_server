package com.modagbul.BE.global.config.fcm.exception;

import com.modagbul.BE.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class FirebaseException extends ApplicationException {
    protected FirebaseException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
