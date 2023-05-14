package com.modagbul.BE.domain.user.service;

import com.google.gson.JsonObject;
import com.modagbul.BE.common.factory.UserFactory;
import com.modagbul.BE.domain.user.constant.UserConstant;
import com.modagbul.BE.domain.user.dto.UserDto;
import com.modagbul.BE.domain.user.dto.UserDto.LoginResponse;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.global.config.jwt.TokenProvider;
import com.modagbul.BE.global.dto.TokenInfoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    TokenProvider tokenProvider;

    @Mock
    Kakao kakao;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(tokenProvider, userRepository, kakao);
    }

    @Test
    void 새로운_사용자를_저장한다() {
        //given
        User mockUser = UserFactory.beforeSignUpUser();
        given(userRepository.findNotDeletedByEmail(anyString())).willReturn(Optional.empty());
        //when
        userService.saveUser(mockUser.getEmail(), mockUser.getImageUrl(), mockUser.getGender(), mockUser.getAgeRange());
        //then
        assertThat(mockUser.getEmail()).isNotNull();
        then(userRepository).should(times(1)).save(any(User.class));
    }

    @Test
    void 이미_있는_사용자를_저장하지_않는다() {
        //given
        User mockUser = UserFactory.beforeSignUpUser();
        given(userRepository.findNotDeletedByEmail(anyString())).willReturn(Optional.of(mockUser));
        //when
        userService.saveUser(mockUser.getEmail(), mockUser.getImageUrl(), mockUser.getGender(), mockUser.getAgeRange());
        //then
        assertThat(mockUser.getEmail()).isNotNull();
        then(userRepository).should(times(0)).save(any(User.class));
    }


    @Test
    void 추가정보입력전_사용자_로그인_테스트() {
        //given
        UserDto.LoginRequest loginRequest = new UserDto.LoginRequest("test_token");
        User mockUser = UserFactory.beforeSignUpUser();
        JsonObject mockUserInfo = new JsonObject();
        TokenInfoResponse mockTokenInfoResponse = new TokenInfoResponse("grant_type", "access_token", "refresh_token", 3600L);

        given(kakao.connectKakao(anyString(), anyString())).willReturn(mockUserInfo);
        given(kakao.getAgeRange(any())).willReturn(mockUser.getAgeRange());
        given(kakao.getEmail(any())).willReturn(mockUser.getEmail());
        given(kakao.getGender(any())).willReturn(mockUser.getGender());
        given(kakao.getPictureUrl(any())).willReturn(mockUser.getImageUrl());
        given(userRepository.findNotDeletedByEmail(anyString())).willReturn(Optional.of(mockUser));
        given(tokenProvider.createToken(any(OAuth2AuthenticationToken.class), anyBoolean())).willReturn(mockTokenInfoResponse);

        //when
        LoginResponse result = userService.login(loginRequest);

        //then
        assertThat(result.getAccessToken()).isEqualTo(mockTokenInfoResponse.getAccessToken());
        assertThat(result.getRefreshToken()).isEqualTo(mockTokenInfoResponse.getRefreshToken());
        assertThat(result.getProcess()).isEqualTo(UserConstant.Process.SIGN_UP_ING.getMessage());
    }

    @Test
    void 추가정보입력후_사용자_로그인_테스트() {
        //given
        UserDto.LoginRequest loginRequest = new UserDto.LoginRequest("test_token");
        User mockUser = UserFactory.afterSignUpUser();
        JsonObject mockUserInfo = new JsonObject();
        TokenInfoResponse mockTokenInfoResponse = new TokenInfoResponse("grant_type", "access_token", "refresh_token", 3600L);

        given(kakao.connectKakao(anyString(), anyString())).willReturn(mockUserInfo);
        given(kakao.getAgeRange(any())).willReturn(mockUser.getAgeRange());
        given(kakao.getEmail(any())).willReturn(mockUser.getEmail());
        given(kakao.getGender(any())).willReturn(mockUser.getGender());
        given(kakao.getPictureUrl(any())).willReturn(mockUser.getImageUrl());
        given(userRepository.findNotDeletedByEmail(anyString())).willReturn(Optional.of(mockUser));
        given(tokenProvider.createToken(any(OAuth2AuthenticationToken.class), anyBoolean())).willReturn(mockTokenInfoResponse);

        //when
        LoginResponse result = userService.login(loginRequest);

        //then
        assertThat(result.getAccessToken()).isEqualTo(mockTokenInfoResponse.getAccessToken());
        assertThat(result.getRefreshToken()).isEqualTo(mockTokenInfoResponse.getRefreshToken());
        assertThat(result.getProcess()).isEqualTo(UserConstant.Process.LOGIN_SUCCESS.getMessage());
    }

    @Test
    void 추가정보를_입력한다() {
        //given
        UserDto.AdditionInfoRequest additionInfoRequest = new UserDto.AdditionInfoRequest("access-token", "밍수", "위례동");
        User mockUser = UserFactory.beforeSignUpUser();
        TokenInfoResponse mockTokenInfoResponse = new TokenInfoResponse("grant_type", "access_token", "refresh_token", 3600L);
        Authentication mockAuthentication = new TestingAuthenticationToken(mockUser.getEmail(), null);
        given(tokenProvider.getAuthentication(anyString())).willReturn(mockAuthentication);
        given(userRepository.findNotDeletedByEmail(anyString())).willReturn(Optional.of(mockUser));
        given(tokenProvider.createToken(any(OAuth2AuthenticationToken.class), anyBoolean())).willReturn(mockTokenInfoResponse);

        //when
        LoginResponse result = userService.signup(additionInfoRequest);

        //then
        assertThat(result.getAccessToken()).isEqualTo(mockTokenInfoResponse.getAccessToken());
        assertThat(result.getRefreshToken()).isEqualTo(mockTokenInfoResponse.getRefreshToken());
        assertThat(result.getProcess()).isEqualTo(UserConstant.Process.LOGIN_SUCCESS.getMessage());
        then(userRepository).should(times(1)).save(any(User.class));
    }

    @Test
    void deleteAccount() {
    }

    @Test
    void checkNickname() {
    }

    @Test
    void validateEmail() {
    }
}