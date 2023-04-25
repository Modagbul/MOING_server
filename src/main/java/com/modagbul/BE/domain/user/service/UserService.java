package com.modagbul.BE.domain.user.service;

import com.modagbul.BE.domain.user.dto.UserDto;
import com.modagbul.BE.domain.user.dto.UserDto.CheckNicknameResponse;
import com.modagbul.BE.domain.user.dto.UserDto.LoginResponse;
import com.modagbul.BE.domain.user.entity.User;


public interface UserService{
    LoginResponse login(UserDto.LoginRequest loginRequest);
    LoginResponse signup(UserDto.AdditionInfoRequest additionInfoRequest);
    void deleteAccount(UserDto.LoginRequest loginRequest);
    CheckNicknameResponse checkNickname(String nickName);
    User validateEmail(String email);
}