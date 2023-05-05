package com.modagbul.BE.domain.vote.board.service;

import com.modagbul.BE.domain.vote.board.dto.VoteDto;
import com.modagbul.BE.domain.vote.board.dto.VoteDto.CreateVoteRequest;
import com.modagbul.BE.domain.vote.board.dto.VoteDto.CreateVoteResponse;

public interface VoteService {
    CreateVoteResponse createVote(Long teamId, CreateVoteRequest createVoteRequest);
}
