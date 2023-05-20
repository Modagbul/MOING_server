package com.modagbul.BE.domain.user.service;

import com.modagbul.BE.domain.user.dto.UserDto;
import com.modagbul.BE.domain.user.dto.UserDto.*;
import com.modagbul.BE.domain.user.entity.User;
import org.hibernate.annotations.Check;


public interface UserService{
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse signup(UserDto.AdditionInfoRequest additionInfoRequest);
    void logout(UserDto.LoginRequest loginRequest);
    void deleteAccount(DeleteAccountRequest deleteAccountRequest);
    CheckNicknameResponse checkNickname(String nickName);
    User validateEmail(String email);
    LoginResponse testLogin(TestLoginRequest testLoginRequest);
    MyPageInfoDto getUserInfo();
    MyPageEditDto updateUserInfo(MyPageEditDto myPageEditDto );
    AlarmDto getAlarmSetting();
    AlarmChangeDto changeNewUploadAlarm(AlarmChangeDto alarmChangeDto);
    AlarmChangeDto changeRemindAlarm(AlarmChangeDto alarmChangeDto);
    AlarmChangeDto changeFireAlarm(AlarmChangeDto alarmChangeDto);
}