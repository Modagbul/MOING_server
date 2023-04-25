package com.modagbul.BE.domain.user.exception;

import com.modagbul.BE.domain.user.constant.UserConstant;

import static com.modagbul.BE.domain.user.constant.UserConstant.UserExceptionList.NOT_HAVE_EMAIL_ERROR;

public class NotHaveEmailException extends UserException{
    public NotHaveEmailException(){
        super(NOT_HAVE_EMAIL_ERROR.getErrorCode(),
                NOT_HAVE_EMAIL_ERROR.getHttpStatus(),
                NOT_HAVE_EMAIL_ERROR.getMessage());
    }
}
