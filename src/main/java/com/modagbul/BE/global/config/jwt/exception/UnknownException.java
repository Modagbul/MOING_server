package com.modagbul.BE.global.config.jwt.exception;

import static com.modagbul.BE.global.config.jwt.constants.JwtConstants.JWTExceptionList.UNKNOWN_ERROR;

public class UnknownException extends JwtException{
    public UnknownException(){
        super(UNKNOWN_ERROR.getErrorCode(),
                UNKNOWN_ERROR.getHttpStatus(),
                UNKNOWN_ERROR.getMessage());
    }
}
