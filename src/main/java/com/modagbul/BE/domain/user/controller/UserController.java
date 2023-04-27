package com.modagbul.BE.domain.user.controller;

import com.modagbul.BE.domain.user.dto.UserDto;
import com.modagbul.BE.domain.user.dto.UserDto.CheckNicknameResponse;
import com.modagbul.BE.domain.user.dto.UserDto.LoginRequest;
import com.modagbul.BE.domain.user.dto.UserDto.LoginResponse;
import com.modagbul.BE.domain.user.service.UserService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.modagbul.BE.domain.user.constant.UserConstant.EUserResponseMessage.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
@Api(tags = "User API")
public class UserController {

    private UserService userService;

    @ApiOperation(value = "카카오 로그인", notes = "카카오 로그인을 합니다.")
    @PostMapping("/signin")
    public ResponseEntity<ResponseDto<LoginResponse>> signin(@Valid @RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),LOGIN_SUCCES.getMessage(),this.userService.login(loginRequest)));
    }

    @ApiOperation(value="닉네임 중복 검사", notes="닉네임 중복 검사를 합니다.")
    @GetMapping("/check/{nickName}")
    public ResponseEntity<ResponseDto<CheckNicknameResponse>> checkNickname(@PathVariable String nickName){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), CHECK_NICKNAME.getMessage(), this.userService.checkNickname(nickName)));
    }

    @ApiOperation(value="추가 정보 입력", notes="추가 정보를 입력합니다.")
    @PostMapping("/signup")
    public ResponseEntity<ResponseDto<LoginResponse>> kakaoSingup(@Valid @RequestBody UserDto.AdditionInfoRequest additionInfoRequest){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), SIGN_UP_SUCCESS.getMessage(),this.userService.signup(additionInfoRequest)));
    }

    @ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴를 합니다.")
    @PostMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@Valid @RequestBody LoginRequest loginRequest){
        this.userService.deleteAccount(loginRequest);
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), DELETE_SUCCESS.getMessage()));
    }

}