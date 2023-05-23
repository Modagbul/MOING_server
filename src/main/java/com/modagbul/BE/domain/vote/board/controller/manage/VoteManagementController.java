package com.modagbul.BE.domain.vote.board.controller.manage;

import com.modagbul.BE.domain.vote.board.dto.VoteDto;
import com.modagbul.BE.domain.vote.board.service.manage.VoteManagementService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.modagbul.BE.domain.vote.board.constant.VoteConstant.EVoteResponseMessage.CLOSE_VOTE_SUCCESS;
import static com.modagbul.BE.domain.vote.board.constant.VoteConstant.EVoteResponseMessage.DO_VOTE_SUCCESS;

@RestController
@RequiredArgsConstructor
@Api(tags = "Vote API")
@RequestMapping("/api/v1/{teamId}/vote")
public class VoteManagementController {

    private final VoteManagementService voteManagementService;

    @ApiOperation(value = "투표하기", notes = "투표를 합니다.")
    @PutMapping("/{voteId}")
    public ResponseEntity<ResponseDto<VoteDto.GetVoteDetailsResponse>> doVote(@PathVariable Long teamId, @PathVariable Long voteId, @Valid @RequestBody VoteDto.DoVoteRequest doVoteRequest) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), DO_VOTE_SUCCESS.getMessage(), voteManagementService.doVote(teamId, voteId,doVoteRequest)));
    }

    @ApiOperation(value = "투표 종료", notes = "투표를 삭제합니다.")
    @DeleteMapping("/{voteId}")
    public ResponseEntity<ResponseDto> closeVote(@PathVariable Long teamId, @PathVariable Long voteId) {
        this.voteManagementService.closeVote(teamId, voteId);
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), CLOSE_VOTE_SUCCESS.getMessage()));
    }
}
