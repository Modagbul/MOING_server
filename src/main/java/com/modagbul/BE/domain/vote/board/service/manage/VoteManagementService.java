package com.modagbul.BE.domain.vote.board.service.manage;

import com.modagbul.BE.domain.vote.board.dto.VoteDto;

public interface VoteManagementService {
    VoteDto.GetVoteDetailsResponse doVote(Long teamId, Long voteId, VoteDto.DoVoteRequest doVoteRequest);
    void closeVote(Long teamId, Long voteId);
}
