package com.modagbul.BE.domain.vote.board.service;

import com.modagbul.BE.domain.vote.board.dto.VoteDto;
import com.modagbul.BE.domain.vote.board.dto.VoteDto.CreateVoteRequest;
import com.modagbul.BE.domain.vote.board.dto.VoteDto.CreateVoteResponse;
import com.modagbul.BE.domain.vote.board.dto.VoteDto.DoVoteRequest;
import com.modagbul.BE.domain.vote.board.dto.VoteDto.GetVoteDetailsResponse;
import com.modagbul.BE.domain.vote.board.entity.Vote;

public interface VoteService {
    CreateVoteResponse createVote(Long teamId, CreateVoteRequest createVoteRequest);
    void doVote(Long voteId, DoVoteRequest doVoteRequest);
    GetVoteDetailsResponse getVoteDetail(Long voteId);
    void deleteVote(Long voteId);
    Vote validateVote(Long voteId);
}
