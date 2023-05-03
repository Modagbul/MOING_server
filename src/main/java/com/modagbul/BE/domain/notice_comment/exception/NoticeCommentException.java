package com.modagbul.BE.domain.notice_comment.exception;

import com.modagbul.BE.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class NoticeCommentException extends ApplicationException {
    public NoticeCommentException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
