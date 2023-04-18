package com.modagbul.BE.fcm.exception;

import static com.modagbul.BE.fcm.constants.FcmConstants.FirebaseExceptionList.MESSAGING_ERROR;

public class MessagingException extends FirebaseException{
    public MessagingException(String message){
        super(MESSAGING_ERROR.getErrorCode(),
                MESSAGING_ERROR.getHttpStatus(),
                message);
    }
}
