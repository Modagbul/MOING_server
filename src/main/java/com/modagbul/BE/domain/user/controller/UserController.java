package com.modagbul.BE.domain.user.controller;

import com.modagbul.BE.domain.user.dto.UserDto.*;
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
    @PostMapping("/auth/kakao")
    public ResponseEntity<ResponseDto<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), LOGIN_SUCCESS.getMessage(), this.userService.login(loginRequest)));
    }

    @ApiOperation(value = "닉네임 중복 검사", notes = "닉네임 중복 검사를 합니다.")
    @GetMapping("/nickname/{nickName}/available")
    public ResponseEntity<ResponseDto<CheckNicknameResponse>> checkNickname(@PathVariable String nickName) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), CHECK_NICKNAME.getMessage(), this.userService.checkNickname(nickName)));
    }

    @ApiOperation(value = "추가 정보 입력", notes = "추가 정보를 입력합니다.")
    @PostMapping("/additional-info")
    public ResponseEntity<ResponseDto<LoginResponse>> additionalInfo(@Valid @RequestBody AdditionInfoRequest additionInfoRequest) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), SIGN_UP_SUCCESS.getMessage(), this.userService.signup(additionInfoRequest)));
    }

    @ApiOperation(value="추가 정보 입력 검사", notes="추가 정보 입력 여부를 검사합니다.")
    @GetMapping("/additional-info")
    public ResponseEntity<ResponseDto> checkAdditionalInfo(){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), CHECK_ADDITIONALINFO_SUCCESS.getMessage()));
    }


    @ApiOperation(value = "회원 탈퇴", notes = "회원 탈퇴를 합니다.")
    @DeleteMapping
    public ResponseEntity<ResponseDto> delete(@Valid @RequestBody DeleteAccountRequest deleteAccountRequest) {
        this.userService.deleteAccount(deleteAccountRequest);
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), DELETE_SUCCESS.getMessage()));
    }


    @ApiOperation(value = "로그아웃", notes = "로그아웃을 합니다.")
    @PostMapping("/logout")
    public ResponseEntity<ResponseDto> logout(@Valid @RequestBody LoginRequest loginRequest){
        this.userService.logout(loginRequest);
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),LOGOUT_SUCCESS.getMessage()));
    }


    //테스트를 위한 API
    @PostMapping("/auth/test")
    public ResponseEntity<ResponseDto<LoginResponse>> login(@Valid @RequestBody TestLoginRequest testLoginRequest) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), LOGIN_SUCCESS.getMessage(), this.userService.testLogin(testLoginRequest)));
    }


    @GetMapping("/mypage")
    public ResponseEntity<ResponseDto<MyPageInfoDto>> getMypage() {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), MYPAGE_GET_SUCCESS.getMessage(), this.userService.getUserInfo()));
    }

    @PutMapping("/mypage")
    public ResponseEntity<ResponseDto<MyPageEditDto>> editMypage(@Valid @RequestBody MyPageEditDto myPageEditDto) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), MYPAGE_UPDATE_SUCCESS.getMessage(), this.userService.updateUserInfo(myPageEditDto)));
    }

    @GetMapping("/alarm-setting")
    public ResponseEntity<ResponseDto<AlarmDto>> getAlarmSetting() {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), ALARM_GET_SUCCESS.getMessage(), this.userService.getAlarmSetting()));
    }

    @PutMapping("/alarm-setting/new-upload")
    public ResponseEntity<ResponseDto<AlarmChangeDto>> changeNoticeAlarm(@Valid @RequestBody AlarmChangeDto alarmChangeDto) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), ALARM_UPDATE_SUCCESS.getMessage(), this.userService.changeNewUploadAlarm(alarmChangeDto)));
    }

    @PutMapping("/alarm-setting/remind")
    public ResponseEntity<ResponseDto<AlarmChangeDto>> changeRemindAlarm(@Valid @RequestBody AlarmChangeDto alarmChangeDto) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), ALARM_UPDATE_SUCCESS.getMessage(), this.userService.changeRemindAlarm(alarmChangeDto)));
    }

    @PutMapping("/alarm-setting/fire")
    public ResponseEntity<ResponseDto<AlarmChangeDto>> changeFireAlarm(@Valid @RequestBody AlarmChangeDto alarmChangeDto) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), ALARM_UPDATE_SUCCESS.getMessage(), this.userService.changeFireAlarm(alarmChangeDto)));
    }

    @PutMapping("/profileImg")
    public ResponseEntity<ResponseDto<String>> changeProfileImg(@Valid @RequestBody String profileImg) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), ALARM_UPDATE_SUCCESS.getMessage(), this.userService.changeProfileImg(profileImg)));
    }
}
