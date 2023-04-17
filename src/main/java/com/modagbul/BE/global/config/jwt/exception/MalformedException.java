package com.modagbul.BE.global.config.jwt.exception;

import static com.modagbul.BE.global.config.jwt.constants.JwtConstants.JWTExceptionList.MAL_FORMED_TOKEN;

public class MalformedException extends JwtException{
    public MalformedException(){
        super(
                MAL_FORMED_TOKEN.getErrorCode(),
                MAL_FORMED_TOKEN.getHttpStatus(),
                MAL_FORMED_TOKEN.getMessage()
        );
    }
}
