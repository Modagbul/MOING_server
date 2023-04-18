package com.modagbul.BE.fcm.service;

import com.google.firebase.messaging.*;
import com.modagbul.BE.fcm.dto.FcmDto.ToMultiRequest;
import com.modagbul.BE.fcm.dto.FcmDto.ToSingleRequest;
import com.modagbul.BE.fcm.exception.NotificationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FcmServiceImpl implements FcmService {

    private final FirebaseMessaging firebaseMessaging;

    public FcmServiceImpl(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }
    @Override
    @Async
    @Transactional
    public String sendSingleDevice(ToSingleRequest toSingleRequest) {
        String title=toSingleRequest.getTitle();
        String messageBody=toSingleRequest.getBody();
        String registrationToken=toSingleRequest.getRegistrationToken();

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

    @Override
    public String sendMultipleDevices(ToMultiRequest toMultiRequest) {
        String title=toMultiRequest.getTitle();
        String messageBody=toMultiRequest.getBody();
        List<String> registrationTokens=toMultiRequest.getRegistrationToken();

        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(messageBody)
                .build();

        MulticastMessage message = MulticastMessage.builder()
                .setNotification(notification)
                .addAllTokens(registrationTokens)
                .build();

        try {
            BatchResponse response = firebaseMessaging.sendMulticast(message);
            return response.getSuccessCount() + " 메시지가 성공적으로 전송되었습니다.";
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
