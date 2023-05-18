package com.modagbul.BE.domain.user.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

public class UserConstant {
    @Getter
    @RequiredArgsConstructor
    public enum EUserResponseMessage {
        LOGIN_SUCCESS("카카오 로그인을 했습니다"),
        SIGN_UP_SUCCESS("회원 가입을 완료했습니다"),
        CHECK_NICKNAME("닉네임 중복 검사를 하였습니다"),
        DELETE_SUCCESS("회원 탈퇴를 하였습니다"),
        MYPAGE_UPDATE_SUCCESS("마이페이지 수정이 완료되었습니다"),
        MYPAGE_GET_SUCCESS("마이페이지 로딩이 완료되었습니다"),
        ALARM_GET_SUCCESS("알림 설정 로딩이 완료되었습니다"),
        ALARM_UPDATE_SUCCESS("알림 설정 수정이 완료되었습니다"),
        TOKEN_REFRESH_SUCCESS("토큰 재발급을 완료하였습니다");
        private final String message;
    }

    @Getter
    public enum Role {
        ROLE_USER, ROLE_ADMIN
    }

    @Getter
    @RequiredArgsConstructor
    public enum Process {
        SIGN_UP_ING("회원가입 중-추가 정보를 입력해주세요."),
        SIGN_UP_SUCCESS("회원가입이 완료되었습니다"),
        LOGIN_SUCCESS("로그인이 완료되었습니다");

        private final String message;
    }

    @Getter
    @RequiredArgsConstructor
    public enum UserExceptionList {
        CONNECTION_ERROR("U0001", HttpStatus.UNAUTHORIZED, "AccessToken 값이 잘못되었습니다"),
        NOT_HAVE_EMAIL_ERROR("U0002", HttpStatus.NOT_FOUND, "해당 email로 User를 찾을 수 없습니다."),
        NOT_FOUND_USER_ERROR("U0003", HttpStatus.NOT_FOUND, "해당 id로 User를 찾을 수 없습니다.");
        private final String errorCode;
        private final HttpStatus httpStatus;
        private final String message;
    }

    @Getter
    @RequiredArgsConstructor
    public enum UserServiceMessage {
        LOGIN_URL("https://kapi.kakao.com/v2/user/me"),
        DELETE_URL("https://kapi.kakao.com/v1/user/unlink"),
        KAKAO_ACOUNT("kakao_account"),
        VALID_NICKNAME("가능한 닉네임입니다"),
        EXISTED_NCIKNAME("이미 존재하는 닉네임입니다");
        private final String value;
    }
}
