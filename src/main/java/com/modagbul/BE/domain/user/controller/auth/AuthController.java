package com.modagbul.BE.domain.user.controller.auth;

import com.modagbul.BE.domain.user.dto.UserDto;
import com.modagbul.BE.domain.user.service.auth.AuthenticationService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.modagbul.BE.domain.user.constant.UserConstant.EUserResponseMessage.*;
import static com.modagbul.BE.domain.user.constant.UserConstant.EUserResponseMessage.LOGOUT_SUCCESS;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
@Api(tags = "User API")
public class AuthController {

    private final AuthenticationService authenticationService;

    @ApiOperation(value = "카카오 로그인", notes = "카카오 로그인을 합니다.")
    @PostMapping("/auth/kakao")
    public ResponseEntity<ResponseDto<UserDto.LoginResponse>> login(@Valid @RequestBody UserDto.LoginRequest loginRequest) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), LOGIN_SUCCESS.getMessage(), this.authenticationService.login(loginRequest)));
    }

    @ApiOperation(value = "추가 정보 입력", notes = "추가 정보를 입력합니다.")
    @PostMapping("/additional-info")
    public ResponseEntity<ResponseDto<UserDto.LoginResponse>> additionalInfo(@Valid @RequestBody UserDto.AdditionInfoRequest additionInfoRequest) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), SIGN_UP_SUCCESS.getMessage(), this.authenticationService.signup(additionInfoRequest)));
    }


    @ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴를 합니다.")
    @DeleteMapping
    public ResponseEntity<ResponseDto> delete(@Valid @RequestBody UserDto.DeleteAccountRequest deleteAccountRequest) {
        this.authenticationService.deleteAccount(deleteAccountRequest);
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), DELETE_SUCCESS.getMessage()));
    }


    @ApiOperation(value = "로그아웃", notes = "로그아웃을 합니다.")
    @PostMapping("/logout")
    public ResponseEntity<ResponseDto> logout(@Valid @RequestBody UserDto.LoginRequest loginRequest){
        this.authenticationService.logout(loginRequest);
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),LOGOUT_SUCCESS.getMessage()));
    }


    //테스트를 위한 API
    @PostMapping("/auth/test")
    public ResponseEntity<ResponseDto<UserDto.LoginResponse>> login(@Valid @RequestBody UserDto.TestLoginRequest testLoginRequest) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), LOGIN_SUCCESS.getMessage(), this.authenticationService.testLogin(testLoginRequest)));
    }
}
