package com.modagbul.BE.domain.vote.board.application.service;

import com.modagbul.BE.domain.vote.board.domain.entity.Vote;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteBoardCloseService {

    private final VoteValidateService voteValidateService;
    public void closeVote(Long teamId, Long voteId) {
        Vote vote = voteValidateService.validateVote(teamId, voteId);
        voteValidateService.validateWriter(SecurityUtils.getLoggedInUser(), vote);
        vote.closeVote();
    }
}
