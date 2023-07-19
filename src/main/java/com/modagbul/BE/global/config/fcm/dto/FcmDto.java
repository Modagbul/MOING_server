package com.modagbul.BE.global.config.fcm.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

public abstract class FcmDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel(description = "단일 기기 전송을 위한 요청 객체")
    public static class ToSingleRequest {
        @NotNull(message = "기기 등록 토큰을 입력해 주세요.")
        @ApiModelProperty(notes = "기기 등록 토큰을 입력해 주세요.")
        private String registrationToken;

        @NotNull(message = "알림 제목을 입력해 주세요.")
        @ApiModelProperty(notes = "알림 제목을 입력해 주세요.")
        private String title;

        @NotNull(message = "알림 내용을 입력해 주세요.")
        @ApiModelProperty(notes = "알림 내용을 입력해 주세요.")
        private String body;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("여러 기기 전송을 위한 요청 객체")
    public static class ToMultiRequest {
        @NotNull(message = "기기 등록 토큰들을 입력해 주세요.")
        @ApiModelProperty(notes = "기기 등록 토큰들을 입력해 주세요.")
        private List<String> registrationToken;

        @NotNull(message = "알림 제목을 입력해 주세요.")
        @ApiModelProperty(notes = "알림 제목을 입력해 주세요.")
        private String title;

        @NotNull(message = "알림 내용을 입력해 주세요.")
        @ApiModelProperty(notes = "알림 내용을 입력해 주세요.")
        private String body;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("주제 전송을 위한 요청 객체 [모두 AND/OR 인 경우]")
    public static class TopicRequest {
        @NotNull(message = "주제들을 입력해 주세요.")
        @ApiModelProperty(notes = "주제들을 입력해 주세요.")
        private List<String> topic;

        @ApiModelProperty(notes = "일괄 처리할 연산자를 입력해 주세요.")
        @Pattern(regexp = "&&|\\|\\|", message = "연산자 형식은 && 또는 || 이어야 합니다.")
        private String operator;

        @NotNull(message = "알림 제목을 입력해 주세요.")
        @ApiModelProperty(notes = "알림 제목을 입력해 주세요.")
        private String title;

        @NotNull(message = "알림 내용을 입력해 주세요.")
        @ApiModelProperty(notes = "알림 내용을 입력해 주세요.")
        private String body;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ApiModel("주제 전송을 위한 요청 객체 [복잡한 경우]")
    public static class TopicCustomRequest {
        @NotNull(message = "조건절을 입력해 주세요.")
        @ApiModelProperty(notes = "조건절을 입력해 주세요.")
        private List<String> conditions;

        @ApiModelProperty(notes = "연산자 리스트를 입력해 주세요.")
        private List<@Pattern(regexp = "&&|\\|\\|", message = "연산자 형식은 && 또는 || 이어야 합니다.") String> operator;

        @NotNull(message = "알림 제목을 입력해 주세요.")
        @ApiModelProperty(notes = "알림 제목을 입력해 주세요.")
        private String title;
        @NotNull(message = "알림 내용을 입력해 주세요.")
        @ApiModelProperty(notes = "알림 내용을 입력해 주세요.")
        private String body;

    }

    @Getter
    @AllArgsConstructor
    @ApiModel("fcm Single 응답 객체")
    public static class SingleFcmResponse {
        private final String response;

    }
    @Getter
    @AllArgsConstructor
    @ApiModel("fcm Multi 응답 객체")
    public static class MultiFcmResponse {
        private final String response;
        private final List<String> failedTokens;

    }

}
