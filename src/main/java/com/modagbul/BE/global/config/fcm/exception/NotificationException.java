package com.modagbul.BE.global.config.fcm.exception;

import static com.modagbul.BE.global.config.fcm.constant.FcmConstant.FirebaseExceptionList.NOTIFICATION_ERROR;

public class NotificationException extends FirebaseException{
    public NotificationException(String message){
        super(NOTIFICATION_ERROR.getErrorCode(),
                NOTIFICATION_ERROR.getHttpStatus(),
                message);
    }
}
