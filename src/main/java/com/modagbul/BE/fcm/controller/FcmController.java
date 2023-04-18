package com.modagbul.BE.fcm.controller;

import com.modagbul.BE.fcm.dto.FcmDto.ToMultiRequest;
import com.modagbul.BE.fcm.dto.FcmDto.ToSingleRequest;
import com.modagbul.BE.fcm.service.FcmService;
import com.modagbul.BE.global.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.modagbul.BE.fcm.constants.FcmConstants.EFCMResponseMessage.SUCCESS_TO_MULTI;
import static com.modagbul.BE.fcm.constants.FcmConstants.EFCMResponseMessage.SUCCESS_TO_SINGLE;

@RestController
@RequestMapping("/api/v1/fcm")
public class FcmController {

    private final FcmService fcmService;


    @Autowired
    public FcmController(FcmService fcmService) {
        this.fcmService=fcmService;
    }

    @PostMapping("/single")
    public ResponseEntity<ResponseDto<String>> sendToSingle(@RequestBody ToSingleRequest request) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), SUCCESS_TO_SINGLE.getMessage(), this.fcmService.sendSingleDevice(request)));
    }

    @PostMapping("/multi")
    public ResponseEntity<ResponseDto<String>> sendToMulti(@RequestBody ToMultiRequest toMultiRequest){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), SUCCESS_TO_MULTI.getMessage(), this.fcmService.sendMultipleDevices(toMultiRequest)));
    }

}

