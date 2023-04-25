package com.modagbul.BE.domain.user.exception;

import com.modagbul.BE.domain.user.constant.UserConstant;

import static com.modagbul.BE.domain.user.constant.UserConstant.UserExceptionList.CONNECTION_ERROR;

public class ConnException extends UserException{

    public ConnException(){
        super(CONNECTION_ERROR.getErrorCode(),
                CONNECTION_ERROR.getHttpStatus(),
                CONNECTION_ERROR.getMessage());
    }
}
