package com.modagbul.BE.global.config.jwt.exception;

import static com.modagbul.BE.global.config.jwt.constants.JwtConstants.JWTExceptionList.ADDITIONAL_REQUIRED_TOKEN;

public class AdditionalInfoException extends JwtException{
    public AdditionalInfoException(){
        super(ADDITIONAL_REQUIRED_TOKEN.getErrorCode(),
                ADDITIONAL_REQUIRED_TOKEN.getHttpStatus(),
                ADDITIONAL_REQUIRED_TOKEN.getMessage());
    }
}
