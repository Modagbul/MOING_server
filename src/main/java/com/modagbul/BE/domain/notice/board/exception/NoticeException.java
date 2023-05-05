package com.modagbul.BE.domain.notice.board.exception;

import com.modagbul.BE.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class NoticeException extends ApplicationException{
    protected NoticeException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
