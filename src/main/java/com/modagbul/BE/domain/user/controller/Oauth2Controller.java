package com.modagbul.BE.domain.user.controller;

import com.modagbul.BE.domain.user.constant.UserConstant;
import com.modagbul.BE.domain.user.dto.UserDto;
import com.modagbul.BE.domain.user.service.UserService;
import com.modagbul.BE.global.dto.ResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class Oauth2Controller {
    private final UserService userService;
    @ResponseBody
    @GetMapping("oauth/kakao")
    public ResponseEntity<ResponseDto<UserDto.LoginResponse>> kakaoCallback(@RequestParam String code) {
        UserDto.LoginRequest loginRequest=new UserDto.LoginRequest(userService.getKakaoAccessToken(code));
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), UserConstant.EUserResponseMessage.LOGIN_SUCCESS.getMessage(),this.userService.login(loginRequest)));
    }
}
