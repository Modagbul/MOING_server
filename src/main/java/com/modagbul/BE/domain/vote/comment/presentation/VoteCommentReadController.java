package com.modagbul.BE.domain.vote.comment.presentation;

import com.modagbul.BE.domain.vote.comment.application.dto.res.VoteCommentResponse.GetVoteCommentResponse;
import com.modagbul.BE.domain.vote.comment.application.service.VoteCommentReadService;
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

import static com.modagbul.BE.domain.vote.comment.presentation.constant.EVoteCommentResponseMessage.GET_VOTE_COMMENT_SUCCESS;


@RestController
@RequiredArgsConstructor
@Api(tags = "Vote Comment API")
@RequestMapping("/api/v1/{teamId}/vote/{voteId}/comment")
public class VoteCommentReadController {

    private final VoteCommentReadService voteCommentReadService;

    @ApiOperation(value = "투표 댓글 조회", notes = "투표 댓글 목록을 최신순으로 조회합니다")
    @GetMapping
    public ResponseEntity<ResponseDto<List<GetVoteCommentResponse>>> getVoteComment(@PathVariable Long teamId, @PathVariable Long voteId) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), GET_VOTE_COMMENT_SUCCESS.getMessage(), voteCommentReadService.getAllVoteCommentByVoteId(teamId, voteId)));
    }

}
