package com.modagbul.BE.fcm.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.modagbul.BE.fcm.exception.NotificationException;
import org.springframework.stereotype.Service;

@Service
public class FcmService {

    private final FirebaseMessaging firebaseMessaging;

    public FcmService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    public String sendNotification(String registrationToken, String title, String messageBody) {

        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(messageBody)
                .build();

        Message message = Message.builder()
                .setNotification(notification)
                .setToken(registrationToken)
                .build();

        try {
            String response = firebaseMessaging.send(message);
            return response;
        } catch (FirebaseMessagingException e) {
            String errorCode = e.getErrorCode().name();
            String errorMessage = e.getMessage();

            // 예외 처리
            switch (errorCode) {
                case "INVALID_ARGUMENT":
                    throw new NotificationException("올바르지 않은 인자 값입니다: " + errorMessage);
                case "NOT_FOUND":
                    throw new NotificationException("등록 토큰이 유효하지 않습니다: " + errorMessage);
                case "UNAVAILABLE":
                    throw new NotificationException("서비스를 사용할 수 없습니다: " + errorMessage);
                default:
                    throw new NotificationException("메시지 전송에 실패했습니다: " + errorMessage);
            }
        }
    }

}

