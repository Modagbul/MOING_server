package com.modagbul.BE.global.config.fcm.exception;

import static com.modagbul.BE.global.config.fcm.constant.FcmConstant.FirebaseExceptionList.INITIALIZE_ERROR;

public class InitializeException extends FirebaseException{
    public InitializeException(){
        super(INITIALIZE_ERROR.getErrorCode(),
                INITIALIZE_ERROR.getHttpStatus(),
                INITIALIZE_ERROR.getMessage()
        );
    }
}
