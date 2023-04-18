package com.modagbul.BE.fcm.controller;

import com.modagbul.BE.fcm.dto.FcmDto;
import com.modagbul.BE.fcm.service.FcmService;
import com.modagbul.BE.global.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.modagbul.BE.fcm.constants.FcmConstants.EFCMResponseMessage.SUCCESS_NOTIFICATION;

@RestController
@RequestMapping("/api/v1/notifications")
public class FcmController {

    private final FcmService fcmService;


    @Autowired
    public FcmController(FcmService fcmService) {
        this.fcmService=fcmService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto<String>> sendNotification(@RequestBody FcmDto.NotificationRequest request) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), SUCCESS_NOTIFICATION.getMessage(), this.fcmService.sendNotification(request.getRegistrationToken(), request.getTitle(), request.getBody())));
    }
}

