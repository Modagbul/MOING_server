package com.modagbul.BE.domain.vote.board.service;

import com.modagbul.BE.domain.vote.board.dto.VoteDto.CreateVoteRequest;
import com.modagbul.BE.domain.vote.board.dto.VoteDto.CreateVoteResponse;
import com.modagbul.BE.domain.vote.board.dto.VoteDto.DoVoteRequest;

public interface VoteService {
    CreateVoteResponse createVote(Long teamId, CreateVoteRequest createVoteRequest);
    void doVote(Long voteId, DoVoteRequest doVoteRequest);
}
