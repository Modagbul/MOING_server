package com.modagbul.BE.fcm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public abstract class FcmDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NotificationRequest {
        private String registrationToken;
        private String title;
        private String body;

    }
}
