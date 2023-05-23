package com.modagbul.BE.domain.vote.board.service.create;

import com.modagbul.BE.domain.vote.board.dto.VoteDto;

public interface VoteCreationService {
    VoteDto.CreateVoteResponse createVote(Long teamId, VoteDto.CreateVoteRequest createVoteRequest);
}
