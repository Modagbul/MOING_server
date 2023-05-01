package com.modagbul.BE.domain.notice.controller;

import com.modagbul.BE.domain.notice.dto.NoticeDto.CreateNoticeRequest;
import com.modagbul.BE.domain.notice.dto.NoticeDto.CreateNoticeResponse;
import com.modagbul.BE.domain.notice.service.NoticeService;
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

import static com.modagbul.BE.domain.notice.constant.NoticeConstant.ENoticeResponseMessage.CREATE_NOTICE_SUCCESS;

@RestController
@RequiredArgsConstructor
@Api(tags = "Notice API")
@RequestMapping("/api/v1/notice")
public class NoticeController {

    private final NoticeService noticeService;

    @ApiOperation(value = "공지 생성", notes = "공지를 생성합니다.")
    @PostMapping
    public ResponseEntity<ResponseDto<CreateNoticeResponse>> createNotice(@Valid @RequestBody CreateNoticeRequest createNoticeRequest){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), CREATE_NOTICE_SUCCESS.getMessage(), noticeService.createNotice(createNoticeRequest)));
    }

}
