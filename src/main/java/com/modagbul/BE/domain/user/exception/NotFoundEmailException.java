package com.modagbul.BE.domain.user.exception;

import static com.modagbul.BE.domain.user.constant.UserConstant.UserExceptionList.*;

public class NotFoundEmailException extends UserException{
    public NotFoundEmailException(){
        super(NOT_FOUND_USER_ERROR.getErrorCode(),
                NOT_FOUND_USER_ERROR.getHttpStatus(),
                NOT_FOUND_USER_ERROR.getMessage());
    }
}
