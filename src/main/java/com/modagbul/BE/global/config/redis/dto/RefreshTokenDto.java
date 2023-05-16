package com.modagbul.BE.global.config.redis.dto;

import com.modagbul.BE.global.dto.TokenInfoResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public abstract class RefreshTokenDto {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "토큰 재발급을 위한 요청 객체")
    public static class RefreshTokenRequest {

        @NotBlank(message = "리프레시 토큰을 입력해주세요.")
        @ApiModelProperty(notes = "리프레시 토큰을 주세요.")
        private String refreshToken;

        @NotNull(message = "유저 id를 입력해 주세요.")
        @ApiModelProperty(notes = "유저 id를 입력해 주세요.")
        private Long userId;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "토큰 재발급을 위한 응답 객체")
    public static class RefreshTokenResponse {
        private String accessToken;
        private String refreshToken;

        public static RefreshTokenResponse from(TokenInfoResponse tokenInfoResponse) {
            return RefreshTokenResponse.builder()
                    .accessToken(tokenInfoResponse.getAccessToken())
                    .refreshToken(tokenInfoResponse.getRefreshToken())
                    .build();
        }
    }
}
