package com.modagbul.BE.fcm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public abstract class FcmDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ToSingleRequest {
        private String registrationToken;
        private String title;
        private String body;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ToMultiRequest {
        private List<String> registrationToken;
        private String title;
        private String body;

    }
}
