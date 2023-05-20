package com.modagbul.BE.domain.user.controller.mypage;

import com.modagbul.BE.domain.user.dto.UserDto;
import com.modagbul.BE.domain.user.service.mypage.MyPageService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.modagbul.BE.domain.user.constant.UserConstant.EUserResponseMessage.*;
import static com.modagbul.BE.domain.user.constant.UserConstant.EUserResponseMessage.ALARM_UPDATE_SUCCESS;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
@Api(tags = "User API")
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping("/mypage")
    public ResponseEntity<ResponseDto<UserDto.MyPageInfoDto>> getMypage() {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), MYPAGE_GET_SUCCESS.getMessage(), this.myPageService.getUserInfo()));
    }

    @PutMapping("/mypage")
    public ResponseEntity<ResponseDto<UserDto.MyPageEditDto>> editMypage(@Valid @RequestBody UserDto.MyPageEditDto myPageEditDto) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), MYPAGE_UPDATE_SUCCESS.getMessage(), this.myPageService.updateUserInfo(myPageEditDto)));
    }

    @GetMapping("/alarm-setting")
    public ResponseEntity<ResponseDto<UserDto.AlarmDto>> getAlarmSetting() {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), ALARM_GET_SUCCESS.getMessage(), this.myPageService.getAlarmSetting()));
    }

    @PutMapping("/alarm-setting/new-upload")
    public ResponseEntity<ResponseDto<UserDto.AlarmChangeDto>> changeNoticeAlarm(@Valid @RequestBody UserDto.AlarmChangeDto alarmChangeDto) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), ALARM_UPDATE_SUCCESS.getMessage(), this.myPageService.changeNewUploadAlarm(alarmChangeDto)));
    }

    @PutMapping("/alarm-setting/remind")
    public ResponseEntity<ResponseDto<UserDto.AlarmChangeDto>> changeRemindAlarm(@Valid @RequestBody UserDto.AlarmChangeDto alarmChangeDto) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), ALARM_UPDATE_SUCCESS.getMessage(), this.myPageService.changeRemindAlarm(alarmChangeDto)));
    }

    @PutMapping("/alarm-setting/fire")
    public ResponseEntity<ResponseDto<UserDto.AlarmChangeDto>> changeFireAlarm(@Valid @RequestBody UserDto.AlarmChangeDto alarmChangeDto) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), ALARM_UPDATE_SUCCESS.getMessage(), this.myPageService.changeFireAlarm(alarmChangeDto)));
    }

    @PutMapping("/profileImg")
    public ResponseEntity<ResponseDto<String>> changeProfileImg(@Valid @RequestBody String profileImg) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), ALARM_UPDATE_SUCCESS.getMessage(), this.myPageService.changeProfileImg(profileImg)));
    }
}
