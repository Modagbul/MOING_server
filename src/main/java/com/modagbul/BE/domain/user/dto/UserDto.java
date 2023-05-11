package com.modagbul.BE.domain.user.dto;

import com.modagbul.BE.global.dto.TokenInfoResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

public abstract class UserDto {

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "카카오 로그인을 위한 요청 객체")
    @NoArgsConstructor
    public static class LoginRequest {
        @NotBlank(message = "카카오 액세스 토큰을 입력해주세요.")
        @ApiModelProperty(notes = "카카오 accessToken을 주세요.")
        private String token;

        public void setToken(String token) {
            this.token = token;
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class TestLoginRequest {

        private String nickName;
        private String address;
        private String email;
        private String gender;
        private String ageRange;
        private String imageUrl;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "로그인을 위한 응답 객체")
    public static class LoginResponse {
        private String accessToken;
        private String refreshToken;
        private String kakaoAccessToken;
        private String process;

        public static LoginResponse from(TokenInfoResponse tokenInfoResponse, String kakaoAccessToken, String process) {
            return LoginResponse.builder()
                    .accessToken(tokenInfoResponse.getAccessToken())
                    .refreshToken(tokenInfoResponse.getRefreshToken())
                    .kakaoAccessToken(kakaoAccessToken)
                    .process(process)
                    .build();
        }
    }
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "추가 정보 입력을 위한 요청 객체")
    public static class AdditionInfoRequest {
        @NotBlank(message = "자체 jwt 액세스 토큰을 입력해주세요.")
        @ApiModelProperty(notes = "자체 액세스 토큰을 입력해 주세요.")
        private String accessToken;

        @NotBlank(message = "닉네임을 입력해주세요.")
        @ApiModelProperty(notes = "닉네임을 입력해 주세요.")
        private String nickName;

        @NotBlank(message = "주소를 입력해주세요.")
        @ApiModelProperty(notes = "주소를 입력해주세요.")
        private String address;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "닉네임 중복 검사를 위한 응답 객체")
    public static class CheckNicknameResponse {
        private String result;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "마이페이지를 수정 위한 객체")
    public static class MyPageEditDto {
        private String nickName;
        private String introduction;
    }
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "마이페이지를 위한 객체")
    public static class MyPageInfoDto {
        private String nickName;
        private String introduction;
        private int teamCount;
        private List<TeamList> teamList;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TeamList {
        private String teamName;
        private String profileUrl;
        private String date;

    }



}
