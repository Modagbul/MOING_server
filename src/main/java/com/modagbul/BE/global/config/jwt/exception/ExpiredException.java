package com.modagbul.BE.global.config.jwt.exception;

import static com.modagbul.BE.global.config.jwt.constants.JwtConstants.JWTExceptionList.EXPIRED_TOKEN;

public class ExpiredException extends JwtException{
    public ExpiredException(){
        super(EXPIRED_TOKEN.getErrorCode(),
                EXPIRED_TOKEN.getHttpStatus(),
                EXPIRED_TOKEN.getMessage()
        );
    }
}
