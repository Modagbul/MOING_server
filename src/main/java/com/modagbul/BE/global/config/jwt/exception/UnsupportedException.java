package com.modagbul.BE.global.config.jwt.exception;

import static com.modagbul.BE.global.config.jwt.constants.JwtConstants.JWTExceptionList.UNSUPPORTED_TOKEN;

public class UnsupportedException extends JwtException {
    public UnsupportedException(){
        super(
                UNSUPPORTED_TOKEN.getErrorCode(),
                UNSUPPORTED_TOKEN.getHttpStatus(),
                UNSUPPORTED_TOKEN.getMessage()
        );
    }
}
