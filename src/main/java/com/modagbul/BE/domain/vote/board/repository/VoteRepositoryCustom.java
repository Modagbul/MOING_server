package com.modagbul.BE.domain.vote.board.repository;

import com.modagbul.BE.domain.vote.board.dto.VoteDto;
import com.modagbul.BE.domain.vote.board.dto.VoteDto.GetUnReadVoteResponse;
import com.modagbul.BE.domain.vote.board.dto.VoteDto.GetVoteAllResponse;
import com.modagbul.BE.domain.vote.board.dto.VoteDto.GetVoteDetailsResponse;
import com.modagbul.BE.domain.vote.board.entity.Vote;

import java.util.List;
import java.util.Optional;

public interface VoteRepositoryCustom {
    Optional<Vote> findNotClosedByVoteId(Long voteId);
    GetVoteDetailsResponse getVoteDetailByVoteId(Long voteId);
    GetVoteAllResponse getVoteAllByTeamId(Long teamId, Long userId);
    List<GetUnReadVoteResponse> getUnReadVoteByTeamId(Long teamId, Long userId);
}
