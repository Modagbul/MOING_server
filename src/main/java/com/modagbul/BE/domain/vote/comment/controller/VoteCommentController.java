package com.modagbul.BE.domain.vote.comment.controller;

import com.modagbul.BE.domain.vote.comment.dto.VoteCommentDto.CreateVoteCommentRequest;
import com.modagbul.BE.domain.vote.comment.dto.VoteCommentDto.CreateVoteCommentResponse;
import com.modagbul.BE.domain.vote.comment.service.VoteCommentService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.modagbul.BE.domain.vote.comment.constant.VoteCommentConstant.EVoteCommentResponseMessage.CREATE_VOTE_COMMENT_SUCCESS;

@RestController
@RequiredArgsConstructor
@Api(tags = "Vote Comment API")
@RequestMapping("/api/v1/{teamId}/vote/{voteId}/comment")
public class VoteCommentController {

    private final VoteCommentService voteCommentService;

    @ApiOperation(value = "투표 댓글 생성", notes = "투표 댓글을 생성합니다.")
    @PostMapping
    public ResponseEntity<ResponseDto<CreateVoteCommentResponse>> createVoteComment(@PathVariable Long teamId, @PathVariable Long voteId, @Valid @RequestBody CreateVoteCommentRequest createVoteCommentRequest){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), CREATE_VOTE_COMMENT_SUCCESS.getMessage(), voteCommentService.createVoteComment(voteId, createVoteCommentRequest)));
    }

    //공지 댓글 삭제

    //공지 댓글 조회
}
