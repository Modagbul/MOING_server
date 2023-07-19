package com.modagbul.BE.domain.vote.board.presentation;

import com.modagbul.BE.domain.vote.board.application.dto.req.VoteRequest.DoVoteRequest;
import com.modagbul.BE.domain.vote.board.application.dto.res.VoteResponse.GetVoteDetailsResponse;
import com.modagbul.BE.domain.vote.board.application.service.VoteBoardDoService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.modagbul.BE.domain.vote.board.presentation.content.EVoteResponseMessage.DO_VOTE_SUCCESS;

@RestController
@RequiredArgsConstructor
@Api(tags = "Vote API")
@RequestMapping("/api/v1/{teamId}/vote")
public class VoteManagementController {

    private final VoteBoardDoService voteBoardDoService;

    @ApiOperation(value = "투표하기", notes = "투표를 합니다.")
    @PutMapping("/{voteId}")
    public ResponseEntity<ResponseDto<GetVoteDetailsResponse>> doVote(@PathVariable Long teamId, @PathVariable Long voteId, @Valid @RequestBody DoVoteRequest doVoteRequest) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), DO_VOTE_SUCCESS.getMessage(), voteBoardDoService.doVote(teamId, voteId, doVoteRequest)));
    }
}
