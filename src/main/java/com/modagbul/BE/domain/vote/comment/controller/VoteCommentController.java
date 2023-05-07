package com.modagbul.BE.domain.vote.comment.controller;

import com.modagbul.BE.domain.vote.comment.dto.VoteCommentDto.CreateVoteCommentRequest;
import com.modagbul.BE.domain.vote.comment.dto.VoteCommentDto.CreateVoteCommentResponse;
import com.modagbul.BE.domain.vote.comment.dto.VoteCommentDto.GetVoteCommentResponse;
import com.modagbul.BE.domain.vote.comment.service.VoteCommentService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.modagbul.BE.domain.vote.comment.constant.VoteCommentConstant.EVoteCommentResponseMessage.*;

@RestController
@RequiredArgsConstructor
@Api(tags = "Vote Comment API")
@RequestMapping("/api/v1/{teamId}/vote/{voteId}/comment")
public class VoteCommentController {

    private final VoteCommentService voteCommentService;

    @ApiOperation(value = "투표 댓글 생성", notes = "투툐 댓글을 생성합니다.")
    @PostMapping
    public ResponseEntity<ResponseDto<CreateVoteCommentResponse>> createVoteComment(@PathVariable Long teamId, @PathVariable Long voteId, @Valid @RequestBody CreateVoteCommentRequest createVoteCommentRequest) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), CREATE_VOTE_COMMENT_SUCCESS.getMessage(), voteCommentService.createVoteComment(voteId, createVoteCommentRequest)));
    }

    @ApiOperation(value = "투표 댓글 삭제", notes = "투표 댓글을 삭제합니다.")
    @DeleteMapping("/{voteCommentId}")
    public ResponseEntity<ResponseDto> deleteVoteComment(@PathVariable Long teamId, @PathVariable Long voteId, @PathVariable Long voteCommentId) {
        voteCommentService.deleteVoteComment(voteCommentId);
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), DELETE_VOTE_COMMENT_SUCCESS.getMessage()));
    }

    @ApiOperation(value = "투표 댓글 조회", notes = "투표 댓글 목록을 최신순으로 조회합니다")
    @GetMapping
    public ResponseEntity<ResponseDto<List<GetVoteCommentResponse>>> getVoteComment(@PathVariable Long teamId, @PathVariable Long voteId) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), GET_VOTE_COMMENT_SUCCESS.getMessage(), voteCommentService.getAllVoteCommentByVoteId(voteId)));
    }

}
