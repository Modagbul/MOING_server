package com.modagbul.BE.domain.user.service;

import com.google.gson.JsonObject;
import com.modagbul.BE.common.factory.UserFactory;
import com.modagbul.BE.domain.user.constant.UserConstant;
import com.modagbul.BE.domain.user.dto.UserDto;
import com.modagbul.BE.domain.user.dto.UserDto.*;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.global.config.jwt.TokenProvider;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import com.modagbul.BE.global.dto.TokenInfoResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

import java.util.Optional;

import static com.modagbul.BE.domain.user.constant.UserConstant.UserServiceMessage.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mockStatic;
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
    KakaoAPIConnector kakao;

    User beforeSignUpUser;
    User afterSignUpUser;
    UserDto.LoginRequest loginRequest;
    AdditionInfoRequest additionInfoRequest;
    DeleteAccountRequest deleteAccountRequest;
    JsonObject mockUserInfo = new JsonObject();
    TokenInfoResponse mockTokenInfoResponse;
    Authentication mockAuthentication;
    String nickname;
    private static MockedStatic<SecurityUtils> securityUtilsMock;

    @BeforeEach
    void setUp() {
        beforeSignUpUser = UserFactory.beforeSignUpUser();
        afterSignUpUser = UserFactory.afterSignUpUser();
        loginRequest = new LoginRequest("test_token");
        mockTokenInfoResponse = new TokenInfoResponse("grant_type", "access_token", "refresh_token", 3600L);
        additionInfoRequest = new AdditionInfoRequest("access-token", "밍수", "위례동", "fcmToken");
        mockAuthentication = new TestingAuthenticationToken(beforeSignUpUser.getEmail(), null);
        deleteAccountRequest = new DeleteAccountRequest("token", "탈퇴이유");
        nickname = "nickname";
    }

    @Test
    void 새로운_사용자를_저장한다() {
        //given
        given(userRepository.findNotDeletedByEmail(beforeSignUpUser.getEmail())).willReturn(Optional.empty());
        //when
        userService.saveUser(beforeSignUpUser.getEmail(), beforeSignUpUser.getImageUrl(), beforeSignUpUser.getGender(), beforeSignUpUser.getAgeRange());
        //then
        assertThat(beforeSignUpUser.getEmail()).isNotNull();
        then(userRepository).should(times(1)).save(any(User.class));
    }

    @Test
    void 이미_있는_사용자를_저장하지_않는다() {
        //given
        given(userRepository.findNotDeletedByEmail(afterSignUpUser.getEmail())).willReturn(Optional.of(afterSignUpUser));
        //when
        userService.saveUser(afterSignUpUser.getEmail(), afterSignUpUser.getImageUrl(), afterSignUpUser.getGender(), afterSignUpUser.getAgeRange());
        //then
        assertThat(afterSignUpUser.getEmail()).isNotNull();
        then(userRepository).should(times(0)).save(any(User.class));
    }


    @Test
    void 추가정보입력전_사용자_로그인_테스트() {
        //given
        given(kakao.connectKakao(LOGIN_URL.getValue(), loginRequest.getToken())).willReturn(mockUserInfo);
        given(kakao.getAgeRange(mockUserInfo)).willReturn(beforeSignUpUser.getAgeRange());
        given(kakao.getEmail(mockUserInfo)).willReturn(beforeSignUpUser.getEmail());
        given(kakao.getGender(mockUserInfo)).willReturn(beforeSignUpUser.getGender());
        given(kakao.getPictureUrl(mockUserInfo)).willReturn(beforeSignUpUser.getImageUrl());
        given(userRepository.findNotDeletedByEmail(beforeSignUpUser.getEmail())).willReturn(Optional.of(beforeSignUpUser));
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
        given(kakao.connectKakao(LOGIN_URL.getValue(), loginRequest.getToken())).willReturn(mockUserInfo);
        given(kakao.getAgeRange(mockUserInfo)).willReturn(afterSignUpUser.getAgeRange());
        given(kakao.getEmail(mockUserInfo)).willReturn(afterSignUpUser.getEmail());
        given(kakao.getGender(mockUserInfo)).willReturn(afterSignUpUser.getGender());
        given(kakao.getPictureUrl(mockUserInfo)).willReturn(afterSignUpUser.getImageUrl());
        given(userRepository.findNotDeletedByEmail(afterSignUpUser.getEmail())).willReturn(Optional.of(afterSignUpUser));
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
        given(tokenProvider.getAuthentication(additionInfoRequest.getAccessToken())).willReturn(mockAuthentication);
        given(userRepository.findNotDeletedByEmail(beforeSignUpUser.getEmail())).willReturn(Optional.of(beforeSignUpUser));
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
    void 회원이_탈퇴한다() {
        //given
        securityUtilsMock = mockStatic(SecurityUtils.class);
        securityUtilsMock.when(SecurityUtils::getLoggedInUser).thenReturn(afterSignUpUser);

        given(kakao.connectKakao(DELETE_URL.getValue(), deleteAccountRequest.getToken())).willReturn(mockUserInfo);
        given(userRepository.findNotDeletedByEmail(afterSignUpUser.getEmail())).willReturn(Optional.of(afterSignUpUser));

        //when
        userService.deleteAccount(deleteAccountRequest);

        //then
        then(userRepository).should(times(1)).save(any(User.class));
        securityUtilsMock.close();
    }

    @Test
    void 닉네임이_중복한다() {
        //given
        given(userRepository.findNotDeletedByNickName(nickname)).willReturn(Optional.of(afterSignUpUser));
        //when
        CheckNicknameResponse checkNicknameResponse = userService.checkNickname(nickname);
        //then
        assertThat(checkNicknameResponse.getResult()).isEqualTo(EXISTED_NCIKNAME.getValue());
    }

    @Test
    void 닉네임이_중복하지_않는다() {
        //given
        given(userRepository.findNotDeletedByNickName(nickname)).willReturn(Optional.empty());
        //when
        CheckNicknameResponse checkNicknameResponse = userService.checkNickname(nickname);
        //then
        assertThat(checkNicknameResponse.getResult()).isEqualTo(VALID_NICKNAME.getValue());
    }
}