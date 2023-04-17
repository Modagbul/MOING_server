package com.modagbul.BE.global.config.jwt.exception;

import static com.modagbul.BE.global.config.jwt.constants.JwtConstants.JWTExceptionList.ILLEGAL_TOKEN;

public class IllegalException extends JwtException{
    public IllegalException(){
        super(
                ILLEGAL_TOKEN.getErrorCode(),
                ILLEGAL_TOKEN.getHttpStatus(),
                ILLEGAL_TOKEN.getMessage()
        );
    }
}
