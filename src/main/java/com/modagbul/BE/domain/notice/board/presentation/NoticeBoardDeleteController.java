package com.modagbul.BE.domain.notice.board.presentation;

import com.modagbul.BE.domain.notice.board.application.service.NoticeBoardDeleteService;
import com.modagbul.BE.domain.notice.board.presentation.constant.ENoticeResponseMessage;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = "Notice API")
@RequestMapping("/api/v1/{teamId}/notice")
public class NoticeBoardDeleteController {

    private final NoticeBoardDeleteService noticeBoardDeleteService;

    @ApiOperation(value = "공지 삭제", notes = "공지를 삭제합니다.")
    @DeleteMapping("/{noticeId}")
    public ResponseEntity<ResponseDto> deleteNotice(@PathVariable Long teamId, @PathVariable Long noticeId) {
        this.noticeBoardDeleteService.deleteNotice(teamId, noticeId);
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), ENoticeResponseMessage.DELETE_NOTICE_SUCCESS.getMessage()));
    } //manage
}

