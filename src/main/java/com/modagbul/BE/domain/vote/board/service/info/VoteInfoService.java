package com.modagbul.BE.domain.vote.board.service.info;

import com.modagbul.BE.domain.vote.board.dto.VoteDto;

import java.util.List;

public interface VoteInfoService {
    VoteDto.GetVoteDetailsResponse getVoteDetail(Long teamId, Long voteId);
    VoteDto.GetVoteAllResponse getVoteAll(Long teamId);
    List<VoteDto.GetUnReadVoteResponse> getUnReadVote(Long teamId);
}
