package com.modagbul.BE.fcm.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class FcmConstants {

    @Getter
    @RequiredArgsConstructor
    public enum EFCMResponseMessage{
        SUCCESS_TO_SINGLE("단일 기기 메시지 전송에 성공하였습니다"),
        SUCCESS_TO_MULTI("여러 기기 메시지 전송에 성공하였습니다");
        private final String message;
    }
    @Getter
    @RequiredArgsConstructor
    public enum FirebaseExceptionList{
        INITIALIZE_ERROR("F0001", HttpStatus.INTERNAL_SERVER_ERROR, "Firebase Admin SDK 초기화에 실패했습니다."),
        NOTIFICATION_ERROR("F0002", HttpStatus.INTERNAL_SERVER_ERROR, "메시지 전송에 실패했습니다."),
        MESSAGING_ERROR("F0003", HttpStatus.INTERNAL_SERVER_ERROR, "firebaseConfigPath를 읽어오는데 실패하였습니다");
        private final String errorCode;
        private final HttpStatus httpStatus;
        private final String message;
    }


}
