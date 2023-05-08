package com.modagbul.BE.domain.vote.board.service;

import com.modagbul.BE.domain.vote.board.dto.VoteDto.*;
import com.modagbul.BE.domain.vote.board.entity.Vote;

import java.util.List;

public interface VoteService {
    CreateVoteResponse createVote(Long teamId, CreateVoteRequest createVoteRequest);
    void doVote(Long teamId, Long voteId, DoVoteRequest doVoteRequest);
    GetVoteDetailsResponse getVoteDetail(Long teamId, Long voteId);
    Vote validateVote(Long teamId, Long voteId);
    void closeVote(Long teamId, Long voteId);
    GetVoteAllResponse getVoteAll(Long teamId);
    List<GetUnReadVoteResponse> getUnReadVote(Long teamId);

}
