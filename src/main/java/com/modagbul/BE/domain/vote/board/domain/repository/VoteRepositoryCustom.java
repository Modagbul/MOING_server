package com.modagbul.BE.domain.vote.board.domain.repository;

import com.modagbul.BE.domain.vote.board.application.dto.res.VoteResponse.GetUnReadVoteResponse;
import com.modagbul.BE.domain.vote.board.application.dto.res.VoteResponse.GetVoteAllResponse;
import com.modagbul.BE.domain.vote.board.application.dto.res.VoteResponse.GetVoteDetailsResponse;
import com.modagbul.BE.domain.vote.board.domain.entity.Vote;

import java.util.List;
import java.util.Optional;

public interface VoteRepositoryCustom {
    Optional<Vote> findNotClosedByVoteId(Long voteId);
    GetVoteDetailsResponse findVoteByVoteId(Long voteId);
    GetVoteAllResponse findVoteAllByTeamIdAndUseId(Long teamId, Long userId);
    List<GetUnReadVoteResponse> findUnReadVoteByTeamIdAndUserId(Long teamId, Long userId);
}
