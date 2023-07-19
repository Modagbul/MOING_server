package com.modagbul.BE.domain.notice.comment.presentation;

import com.modagbul.BE.domain.notice.comment.application.dto.req.NoticeCommentRequest.CreateNoticeCommentRequest;
import com.modagbul.BE.domain.notice.comment.application.dto.res.NoticeCommentResponse.CreateNoticeCommentResponse;
import com.modagbul.BE.domain.notice.comment.application.service.NoticeCommentCreateService;
import com.modagbul.BE.domain.notice.comment.presentation.constant.ENoticeCommentResponseMessage;
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
@Api(tags = "Notice Comment API")
@RequestMapping("/api/v1/{teamId}/notice/{noticeId}/comment")
public class NoticeCommentCreateController {

    private final NoticeCommentCreateService noticeCommentCreateService;

    @ApiOperation(value = "공지 댓글 생성", notes = "공지 댓글을 생성합니다.")
    @PostMapping
    public ResponseEntity<ResponseDto<CreateNoticeCommentResponse>> createNoticeComment(@PathVariable Long teamId, @PathVariable Long noticeId, @Valid @RequestBody CreateNoticeCommentRequest createNoticeCommentRequest) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), ENoticeCommentResponseMessage.CREATE_NOTICE_COMMENT_SUCCESS.getMessage(), noticeCommentCreateService.createNoticeComment(teamId, noticeId, createNoticeCommentRequest)));
    }
}
