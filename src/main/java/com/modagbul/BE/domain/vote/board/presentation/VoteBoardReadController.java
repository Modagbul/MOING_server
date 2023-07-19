package com.modagbul.BE.domain.vote.board.presentation;

import com.modagbul.BE.domain.vote.board.application.dto.res.VoteResponse.GetUnReadVoteResponse;
import com.modagbul.BE.domain.vote.board.application.dto.res.VoteResponse.GetVoteAllResponse;
import com.modagbul.BE.domain.vote.board.application.dto.res.VoteResponse.GetVoteDetailsResponse;
import com.modagbul.BE.domain.vote.board.application.service.VoteBoardReadService;
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

import static com.modagbul.BE.domain.vote.board.presentation.content.EVoteResponseMessage.*;


@RestController
@RequiredArgsConstructor
@Api(tags = "Vote API")
@RequestMapping("/api/v1/{teamId}/vote")
public class VoteBoardReadController {

    private final VoteBoardReadService voteBoardReadService;

    @ApiOperation(value = "투표결과 조회", notes = "투표 결과를 조회합니다.")
    @GetMapping("/{voteId}")
    public ResponseEntity<ResponseDto<GetVoteDetailsResponse>> getVoteDetail(@PathVariable Long teamId, @PathVariable Long voteId) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), GET_VOTE_DETAIL_SUCCESS.getMessage(), voteBoardReadService.getVoteDetail(teamId, voteId)));
    }

    @ApiOperation(value = "투표 전체 조회", notes = "투표 블록에서 투표를 전체 조회합니다.")
    @GetMapping
    public ResponseEntity<ResponseDto<GetVoteAllResponse>> getVoteAll(@PathVariable Long teamId) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), GET_VOTE_ALL_SUCCESS.getMessage(), voteBoardReadService.getVoteAll(teamId)));
    }

    @ApiOperation(value = "투표 안읽은 것만 조회", notes = "투표에서 안 읽은 것만 최신순으로 조회합니다.")
    @GetMapping("unread")
    public ResponseEntity<ResponseDto<List<GetUnReadVoteResponse>>> getUnReadVote(@PathVariable Long teamId) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), GET_VOTE_UNREAD_SUCCESS.getMessage(), voteBoardReadService.getUnReadVote(teamId)));
    }
}
