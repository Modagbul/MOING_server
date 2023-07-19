package com.modagbul.BE.domain.vote.board.presentation;

import com.modagbul.BE.domain.vote.board.application.dto.req.VoteRequest;
import com.modagbul.BE.domain.vote.board.application.dto.res.VoteResponse;
import com.modagbul.BE.domain.vote.board.application.service.VoteBoardCreateService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.modagbul.BE.domain.vote.board.presentation.content.EVoteResponseMessage.CREATE_VOTE_SUCCESS;

@RestController
@RequiredArgsConstructor
@Api(tags = "Vote API")
@RequestMapping("/api/v1/{teamId}/vote")
public class VoteBoardCreateController {

    private final VoteBoardCreateService voteBoardCreateService;

    @ApiOperation(value = "투표 생성", notes = "투표를 생성합니다.")
    @PostMapping
    public ResponseEntity<ResponseDto<VoteResponse.CreateVoteResponse>> createVote(@PathVariable Long teamId, @Valid @RequestBody VoteRequest.CreateVoteRequest createVoteRequest) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), CREATE_VOTE_SUCCESS.getMessage(), voteBoardCreateService.createVote(teamId, createVoteRequest)));
    }
}
