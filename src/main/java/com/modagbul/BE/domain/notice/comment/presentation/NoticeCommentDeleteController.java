package com.modagbul.BE.domain.notice.comment.presentation;

import com.modagbul.BE.domain.notice.comment.application.service.NoticeCommentDeleteService;
import com.modagbul.BE.domain.notice.comment.presentation.constant.ENoticeCommentResponseMessage;
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
@Api(tags = "Notice Comment API")
@RequestMapping("/api/v1/{teamId}/notice/{noticeId}/comment")
public class NoticeCommentDeleteController {
    private final NoticeCommentDeleteService noticeCommentDeleteService;

    @ApiOperation(value = "공지 댓글 삭제", notes = "공지 댓글을 삭제합니다.")
    @DeleteMapping("/{noticeCommentId}")
    public ResponseEntity<ResponseDto> deleteNoticeComment(@PathVariable Long teamId, @PathVariable Long noticeId, @PathVariable Long noticeCommentId) {
        noticeCommentDeleteService.deleteNoticeComment(teamId, noticeId, noticeCommentId);
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), ENoticeCommentResponseMessage.DELETE_NOTICE_COMMENT_SUCCESS.getMessage()));
    }
}
