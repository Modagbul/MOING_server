package com.modagbul.BE.domain.vote.comment.presentation;

import com.modagbul.BE.domain.vote.comment.application.dto.req.VoteCommentRequest;
import com.modagbul.BE.domain.vote.comment.application.dto.res.VoteCommentResponse.CreateVoteCommentResponse;
import com.modagbul.BE.domain.vote.comment.application.service.VoteCommentCreateService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.modagbul.BE.domain.vote.comment.presentation.constant.EVoteCommentResponseMessage.CREATE_VOTE_COMMENT_SUCCESS;

@RestController
@RequiredArgsConstructor
@Api(tags = "Vote Comment API")
@RequestMapping("/api/v1/{teamId}/vote/{voteId}/comment")
public class VoteCommentCreateController {

    private final VoteCommentCreateService voteCommentCreateService;

    @ApiOperation(value = "투표 댓글 생성", notes = "투툐 댓글을 생성합니다.")
    @PostMapping
    public ResponseEntity<ResponseDto<CreateVoteCommentResponse>> createVoteComment(@PathVariable Long teamId, @PathVariable Long voteId, @Valid @RequestBody VoteCommentRequest.CreateVoteCommentRequest createVoteCommentRequest) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), CREATE_VOTE_COMMENT_SUCCESS.getMessage(), voteCommentCreateService.createVoteComment(teamId, voteId, createVoteCommentRequest)));
    }

}
