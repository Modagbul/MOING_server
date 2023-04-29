package com.modagbul.BE.fcm.exception;

import static com.modagbul.BE.fcm.constant.FcmConstant.FirebaseExceptionList.INITIALIZE_ERROR;

public class InitializeException extends FirebaseException{
    public InitializeException(){
        super(INITIALIZE_ERROR.getErrorCode(),
                INITIALIZE_ERROR.getHttpStatus(),
                INITIALIZE_ERROR.getMessage()
        );
    }
}
