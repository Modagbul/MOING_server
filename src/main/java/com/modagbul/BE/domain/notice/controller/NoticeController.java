package com.modagbul.BE.domain.notice.controller;

import com.modagbul.BE.domain.notice.dto.NoticeDto;
import com.modagbul.BE.domain.notice.dto.NoticeDto.CreateNoticeRequest;
import com.modagbul.BE.domain.notice.dto.NoticeDto.CreateNoticeResponse;
import com.modagbul.BE.domain.notice.service.NoticeService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.modagbul.BE.domain.notice.constant.NoticeConstant.ENoticeResponseMessage.CREATE_NOTICE_SUCCESS;
import static com.modagbul.BE.domain.notice.constant.NoticeConstant.ENoticeResponseMessage.DELETE_NOTICE_SUCCESS;

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

    @ApiOperation(value = "공지 삭제", notes = "공지를 삭제합니다.")
    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteNotice(@Valid @RequestBody NoticeDto.DeleteNoticeRequest deleteNoticeRequest){
        this.noticeService.deleteNotice(deleteNoticeRequest);
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), DELETE_NOTICE_SUCCESS.getMessage()));
    }


    //공지 단건 조회 (안 읽은 사람까지 조회)

    //공지 전체 조회

    //공지 수정
}
