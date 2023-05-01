package com.modagbul.BE.domain.mission.Exception;

import com.modagbul.BE.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class MissionException extends ApplicationException {

    protected MissionException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
