package com.modagbul.BE.domain.user.service.auth;

import com.modagbul.BE.domain.user.dto.UserDto;
import com.modagbul.BE.domain.user.dto.UserDto.DeleteAccountRequest;
import com.modagbul.BE.domain.user.dto.UserDto.LoginResponse;

public interface UserAuthenticationService {
    LoginResponse login(UserDto.LoginRequest loginRequest);
    LoginResponse signup(UserDto.AdditionInfoRequest additionInfoRequest);
    void logout(UserDto.LoginRequest loginRequest);
    void deleteAccount(DeleteAccountRequest deleteAccountRequest);
    LoginResponse testLogin(UserDto.TestLoginRequest testLoginRequest);
}
