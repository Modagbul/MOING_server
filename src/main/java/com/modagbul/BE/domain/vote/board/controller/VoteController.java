package com.modagbul.BE.domain.vote.board.controller;

import com.modagbul.BE.domain.notice.board.constant.NoticeConstant;
import com.modagbul.BE.domain.notice.board.dto.NoticeDto;
import com.modagbul.BE.domain.vote.board.constant.VoteConstant;
import com.modagbul.BE.domain.vote.board.dto.VoteDto;
import com.modagbul.BE.domain.vote.board.dto.VoteDto.CreateVoteRequest;
import com.modagbul.BE.domain.vote.board.dto.VoteDto.CreateVoteResponse;
import com.modagbul.BE.domain.vote.board.service.VoteService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.modagbul.BE.domain.vote.board.constant.VoteConstant.EVoteResponseMessage.CREATE_VOTE_SUCCESS;

@RestController
@RequiredArgsConstructor
@Api(tags = "Vote API")
@RequestMapping("/api/v1/{teamId}/vote")
public class VoteController {
    private final VoteService voteService;

    @ApiOperation(value = "투표 생성", notes = "투표를 생성합니다.")
    @PostMapping
    public ResponseEntity<ResponseDto<CreateVoteResponse>> createVote(@PathVariable Long teamId, @Valid @RequestBody CreateVoteRequest createVoteRequest) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), CREATE_VOTE_SUCCESS.getMessage(), voteService.createVote(teamId,createVoteRequest)));
    }

}
