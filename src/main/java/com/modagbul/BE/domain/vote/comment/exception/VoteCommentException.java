package com.modagbul.BE.domain.vote.comment.exception;

import com.modagbul.BE.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class VoteCommentException extends ApplicationException {
    protected VoteCommentException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
