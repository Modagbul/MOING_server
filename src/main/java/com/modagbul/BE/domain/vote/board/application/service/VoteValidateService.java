package com.modagbul.BE.domain.vote.board.application.service;

import com.modagbul.BE.domain.team.application.service.TeamValidateService;
import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.vote.board.domain.entity.Vote;
import com.modagbul.BE.domain.vote.board.domain.service.VoteBoardQueryService;
import com.modagbul.BE.domain.vote.board.exception.NotFoundVoteIdException;
import com.modagbul.BE.domain.vote.board.exception.NotVoteWriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteValidateService {

    private final TeamValidateService teamValidateService;
    private final VoteBoardQueryService voteBoardQueryService;


    public Vote validateVote(Long teamId, Long voteId) {
        Team team = teamValidateService.validateTeam(teamId);
        return voteBoardQueryService.getVote(voteId);
    }


    public void validateWriter(User user, Vote vote) {
        if (vote.getUser().getUserId() != user.getUserId())
            throw new NotVoteWriterException();
    }
}
