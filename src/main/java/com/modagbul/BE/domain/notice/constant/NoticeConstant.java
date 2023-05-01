package com.modagbul.BE.domain.notice.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class NoticeConstant {
    @Getter
    @RequiredArgsConstructor
    public enum ENoticeResponseMessage {
        CREATE_NOTICE_SUCCESS("공지를 생성하였습니다");

        private final String message;
    }
}
