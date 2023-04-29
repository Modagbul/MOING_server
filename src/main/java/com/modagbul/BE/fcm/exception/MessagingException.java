package com.modagbul.BE.fcm.exception;

import static com.modagbul.BE.fcm.constant.FcmConstant.FirebaseExceptionList.MESSAGING_ERROR;

public class MessagingException extends FirebaseException{
    public MessagingException(String message){
        super(MESSAGING_ERROR.getErrorCode(),
                MESSAGING_ERROR.getHttpStatus(),
                message);
    }
}
