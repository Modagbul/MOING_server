package com.modagbul.BE.fcm.exception;

import com.modagbul.BE.fcm.constants.FcmConstants;

import static com.modagbul.BE.fcm.constants.FcmConstants.FirebaseExceptionList.NOTIFICATION_ERROR;

public class NotificationException extends FirebaseException{
    public NotificationException(String message){
        super(NOTIFICATION_ERROR.getErrorCode(),
                NOTIFICATION_ERROR.getHttpStatus(),
                message);
    }
}
