package com.modagbul.BE.domain.vote.comment.presentation;

import com.modagbul.BE.domain.vote.comment.application.service.VoteCommentDeleteService;
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

import static com.modagbul.BE.domain.vote.comment.presentation.constant.EVoteCommentResponseMessage.DELETE_VOTE_COMMENT_SUCCESS;

@RestController
@RequiredArgsConstructor
@Api(tags = "Vote Comment API")
@RequestMapping("/api/v1/{teamId}/vote/{voteId}/comment")
public class VoteCommentDeleteController {

    private final VoteCommentDeleteService voteCommentDeleteService;


    @ApiOperation(value = "투표 댓글 삭제", notes = "투표 댓글을 삭제합니다.")
    @DeleteMapping("/{voteCommentId}")
    public ResponseEntity<ResponseDto> deleteVoteComment(@PathVariable Long teamId, @PathVariable Long voteId, @PathVariable Long voteCommentId) {
        voteCommentDeleteService.deleteVoteComment(teamId, voteId, voteCommentId);
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), DELETE_VOTE_COMMENT_SUCCESS.getMessage()));
    }


}
