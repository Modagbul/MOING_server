package com.modagbul.BE.domain.vote.board.presentation;

import com.modagbul.BE.domain.vote.board.application.service.VoteBoardCloseService;
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

import static com.modagbul.BE.domain.vote.board.presentation.content.EVoteResponseMessage.CLOSE_VOTE_SUCCESS;


@RestController
@RequiredArgsConstructor
@Api(tags = "Vote API")
@RequestMapping("/api/v1/{teamId}/vote")
public class VoteBoardCloseController {

    private final VoteBoardCloseService voteBoardCloseService;

    @ApiOperation(value = "투표 종료", notes = "투표를 삭제합니다.")
    @DeleteMapping("/{voteId}")
    public ResponseEntity<ResponseDto> closeVote(@PathVariable Long teamId, @PathVariable Long voteId) {
        this.voteBoardCloseService.closeVote(teamId, voteId);
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), CLOSE_VOTE_SUCCESS.getMessage()));
    }
}
