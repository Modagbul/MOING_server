package com.modagbul.BE.fcm.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class FcmConstant {

    @Getter
    @RequiredArgsConstructor
    public enum EFCMResponseMessage{
        SUCCESS_TO_SINGLE("단일 기기에 메시지를 전송하였습니다."),
        SUCCESS_TO_MULTI("여러 기기에 메시지를 전송하였습니다."),
        SUCCESS_BY_TOPIC("주제를 이용해 메시지를 전송하였습니다");
        private final String message;
    }

    @Getter
    @RequiredArgsConstructor
    public enum NewUploadTitle{
        UPLOAD_NOTICE_NEW_TITLE("[신규 굥지 업로드]"),
        UPLOAD_VOTE_NEW_TITLE("[신규 투표 업로드]");
        private final String title;
    }

    @Getter
    @RequiredArgsConstructor
    public enum NewUploadMessage{
        UPLOAD_NOTICE_NEW_MESSAGE("새로운 공지가 업로드 됐어요!\n빠르게 확인하러 가볼까요?"),
        UPLOAD_VOTE_NEW_MESSAGE("새로운 투표가 업로드 됐어요!\n빠르게 투표하러 가볼까요?");
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
