package com.modagbul.BE.domain.notice.board.presentation;

import com.modagbul.BE.domain.notice.board.application.dto.req.NoticeBoardRequest.CreateNoticeRequest;
import com.modagbul.BE.domain.notice.board.application.dto.res.NoticeBoardResponse.CreateNoticeResponse;
import com.modagbul.BE.domain.notice.board.application.service.NoticeBoardCreateService;
import com.modagbul.BE.domain.notice.board.presentation.constant.ENoticeResponseMessage;
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
public class NoticeBoardCreateController {

    private final NoticeBoardCreateService noticeBoardCreateService;

    @ApiOperation(value = "공지 생성", notes = "공지를 생성합니다.")
    @PostMapping
    public ResponseEntity<ResponseDto<CreateNoticeResponse>> createNotice(@PathVariable Long teamId, @Valid @RequestBody CreateNoticeRequest createNoticeRequest) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), ENoticeResponseMessage.CREATE_NOTICE_SUCCESS.getMessage(), noticeBoardCreateService.createNotice(teamId, createNoticeRequest)));
    }
}