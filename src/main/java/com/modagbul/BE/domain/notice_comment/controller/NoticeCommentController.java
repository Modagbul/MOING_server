package com.modagbul.BE.domain.notice_comment.controller;

import com.modagbul.BE.domain.notice_comment.dto.NoticeCommentDto;
import com.modagbul.BE.domain.notice_comment.dto.NoticeCommentDto.CreateNoticeCommentRequest;
import com.modagbul.BE.domain.notice_comment.dto.NoticeCommentDto.CreateNoticeCommentResponse;
import com.modagbul.BE.domain.notice_comment.dto.NoticeCommentDto.DeleteNoticeCommentRequest;
import com.modagbul.BE.domain.notice_comment.dto.NoticeCommentDto.GetNoticeCommentResponse;
import com.modagbul.BE.domain.notice_comment.service.NoticeCommentService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.modagbul.BE.domain.notice_comment.constant.NoticeCommentConstant.ENoticeCommentResponseMessage.*;

@RestController
@RequiredArgsConstructor
@Api(tags = "Notice Comment API")
@RequestMapping("/api/v1/notice/comment")
public class NoticeCommentController {

    private final NoticeCommentService noticeCommentService;

    @ApiOperation(value = "공지 댓글 생성", notes = "공지 댓글을 생성합니다.")
    @PostMapping
    public ResponseEntity<ResponseDto<CreateNoticeCommentResponse>> createNoticeComment(@Valid @RequestBody CreateNoticeCommentRequest createNoticeCommentRequest){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), CREATE_NOTICE_COMMENT_SUCCESS.getMessage(), noticeCommentService.createNoticeComment(createNoticeCommentRequest)));
    }

    @ApiOperation(value="공지 댓글 삭제", notes="공지 댓글을 삭제합니다.")
    @DeleteMapping
    public ResponseEntity<ResponseDto> deleteNoticeComment(@Valid @RequestBody DeleteNoticeCommentRequest deleteNoticeCommentRequest){
        noticeCommentService.deleteNoticeComment(deleteNoticeCommentRequest);
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), DELETE_NOTICE_COMMENT_SUCCESS.getMessage()));
    }

    @ApiOperation(value="공지 댓글 조회", notes="공지 댓글 목록을 최신순으로 조회합니다")
    @GetMapping("/{noticeId}")
    public ResponseEntity<ResponseDto<List<GetNoticeCommentResponse>>> getNoticeComment(@PathVariable Long noticeId){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), GET_NOTICE_COMMENT_SUCCESS.getMessage(), noticeCommentService.getAllNoticeCommentByNoticeId(noticeId)));
    }
}
