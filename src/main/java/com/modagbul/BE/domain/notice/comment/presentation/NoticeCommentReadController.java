package com.modagbul.BE.domain.notice.comment.presentation;

import com.modagbul.BE.domain.notice.comment.application.dto.res.NoticeCommentResponse;
import com.modagbul.BE.domain.notice.comment.application.service.NoticeCommentReadService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.modagbul.BE.domain.notice.comment.presentation.constant.ENoticeCommentResponseMessage.GET_NOTICE_COMMENT_SUCCESS;

@RestController
@RequiredArgsConstructor
@Api(tags = "Notice Comment API")
@RequestMapping("/api/v1/{teamId}/notice/{noticeId}/comment")
public class NoticeCommentReadController {
    private final NoticeCommentReadService noticeCommentReadService;

    @ApiOperation(value = "공지 댓글 조회", notes = "공지 댓글 목록을 최신순으로 조회합니다")
    @GetMapping
    public ResponseEntity<ResponseDto<List<NoticeCommentResponse.GetNoticeCommentResponse>>> getNoticeComment(@PathVariable Long teamId, @PathVariable Long noticeId) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), GET_NOTICE_COMMENT_SUCCESS.getMessage(), noticeCommentReadService.getAllNoticeCommentByNoticeId(teamId, noticeId)));
    }
}
