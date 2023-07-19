package com.modagbul.BE.global.config.fcm.exception;

import static com.modagbul.BE.global.config.fcm.constant.FcmConstant.FirebaseExceptionList.MESSAGING_ERROR;

public class MessagingException extends FirebaseException{
    public MessagingException(String message){
        super(MESSAGING_ERROR.getErrorCode(),
                MESSAGING_ERROR.getHttpStatus(),
                message);
    }
}
