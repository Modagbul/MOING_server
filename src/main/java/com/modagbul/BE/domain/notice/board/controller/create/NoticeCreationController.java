package com.modagbul.BE.domain.notice.board.controller.create;

import com.modagbul.BE.domain.notice.board.constant.NoticeConstant;
import com.modagbul.BE.domain.notice.board.dto.NoticeDto;
import com.modagbul.BE.domain.notice.board.service.create.NoticeCreationService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Api(tags = "Notice API")
@RequestMapping("/api/v1/{teamId}/notice")
public class NoticeCreationController {

    private final NoticeCreationService noticeCreationService;

    @ApiOperation(value = "공지 생성", notes = "공지를 생성합니다.")
    @PostMapping
    public ResponseEntity<ResponseDto<NoticeDto.CreateNoticeResponse>> createNotice(@PathVariable Long teamId, @Valid @RequestBody NoticeDto.CreateNoticeRequest createNoticeRequest) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), NoticeConstant.ENoticeResponseMessage.CREATE_NOTICE_SUCCESS.getMessage(), noticeCreationService.createNotice(teamId, createNoticeRequest)));
    }
}
