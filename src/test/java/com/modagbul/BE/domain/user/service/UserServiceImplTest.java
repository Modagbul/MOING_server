package com.modagbul.BE.domain.user.service;

import com.modagbul.BE.domain.user.dto.UserDto;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.global.config.jwt.TokenProvider;
import com.modagbul.BE.global.dto.TokenInfoResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static com.modagbul.BE.domain.user.constant.UserConstant.Process.SIGN_UP_ING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private TokenProvider tokenProvider;
    @InjectMocks
    private UserService userService;

    @Test
    void loginSuccessFirstTime() {
        // Given
        String TOKEN="Pgc-cNP9J6hEOCZ8zOSEb9CE5kmfmigWu7Se2ODwCj1y6gAAAYe5Bc0V";
        UserDto.LoginRequest loginRequest = new UserDto.LoginRequest(TOKEN);

        // When
        UserDto.LoginResponse loginResponse = userService.login(loginRequest);

        // Then
        assertEquals(SIGN_UP_ING.getMessage(), loginResponse.getProcess());
    }

    @Test
    void signup() {
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