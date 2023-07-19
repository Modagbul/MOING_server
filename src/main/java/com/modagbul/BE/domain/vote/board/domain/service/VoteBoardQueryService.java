package com.modagbul.BE.domain.vote.board.domain.service;

import com.modagbul.BE.domain.vote.board.application.dto.res.VoteResponse;
import com.modagbul.BE.domain.vote.board.application.dto.res.VoteResponse.GetVoteAllResponse;
import com.modagbul.BE.domain.vote.board.application.dto.res.VoteResponse.GetVoteDetailsResponse;
import com.modagbul.BE.domain.vote.board.domain.entity.Vote;
import com.modagbul.BE.domain.vote.board.domain.repository.VoteRepository;
import com.modagbul.BE.domain.vote.board.exception.NotFoundVoteIdException;
import com.modagbul.BE.global.annotation.DomainService;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

@DomainService
@RequiredArgsConstructor
@Transactional
public class VoteBoardQueryService {
    private final VoteRepository voteRepository;

    public GetVoteAllResponse getVoteAll(Long teamId) {
        return voteRepository.findVoteAllByTeamIdAndUseId(teamId, SecurityUtils.getLoggedInUser().getUserId());
    }


    public List<VoteResponse.GetUnReadVoteResponse> getUnReadVote(Long teamId) {
        return voteRepository.findUnReadVoteByTeamIdAndUserId(teamId, SecurityUtils.getLoggedInUser().getUserId());
    }

    public GetVoteDetailsResponse getVoteDetails(Long voteId){
        return  voteRepository.findVoteByVoteId(voteId);
    }

    public Vote getVote(Long voteId) {
        return this.voteRepository.findNotClosedByVoteId(voteId).orElseThrow(() -> new NotFoundVoteIdException());
    }
}

