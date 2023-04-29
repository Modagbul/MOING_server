package com.modagbul.BE.fcm.controller;

import com.modagbul.BE.fcm.dto.FcmDto.*;
import com.modagbul.BE.fcm.service.FcmService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.modagbul.BE.fcm.constant.FcmConstant.EFCMResponseMessage.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fcm")
@Api(tags = "Fcm API")
public class FcmController {

    private final FcmService fcmService;

    @ApiOperation(value = "단일 기기 전송", notes = "단일 기기에 전송합니다")
    @PostMapping("/single")
    public ResponseEntity<ResponseDto<SingleFcmResponse>> sendToSingle(@Valid @RequestBody ToSingleRequest request) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), SUCCESS_TO_SINGLE.getMessage(), this.fcmService.sendSingleDevice(request)));
    }

    @ApiOperation(value = "여러 기기에 전송", notes = "여러 기기에 전송합니다.")
    @PostMapping("/multi")
    public ResponseEntity<ResponseDto<MultiFcmResponse>> sendToMulti(@Valid @RequestBody ToMultiRequest toMultiRequest){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), SUCCESS_TO_MULTI.getMessage(), this.fcmService.sendMultipleDevices(toMultiRequest)));
    }

    @ApiOperation(value = "주제로 전송", notes = "주제(Topic)로 전송합니다.")
    @PostMapping("/topic")
    public ResponseEntity<ResponseDto<SingleFcmResponse>> sendByTopic(@Valid @RequestBody TopicRequest topicRequest){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), SUCCESS_BY_TOPIC.getMessage(), this.fcmService.sendByTopic(topicRequest)));
    }

    @ApiOperation(value="주제로 전송", notes="복잡한 조건절을 이용해 주제로 전송합니다")
    @PostMapping("/topic/custom")
    public ResponseEntity<ResponseDto<SingleFcmResponse>> sendByCustomTopic(@Valid @RequestBody TopicCustomRequest topicCustomRequest){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), SUCCESS_BY_TOPIC.getMessage(),this.fcmService.sendByTopicCustom(topicCustomRequest)));
    }

}

