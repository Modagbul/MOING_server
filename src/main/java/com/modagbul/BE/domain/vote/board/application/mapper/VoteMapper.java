package com.modagbul.BE.domain.vote.board.application.mapper;

import com.modagbul.BE.domain.team.application.service.TeamValidateService;
import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.domain.vote.board.application.dto.req.VoteRequest.CreateVoteRequest;
import com.modagbul.BE.domain.vote.board.domain.entity.Vote;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VoteMapper {

    private final TeamValidateService teamValidateService;

    private final UserRepository userRepository;

    public Vote toEntity(Long teamId, CreateVoteRequest createVoteRequest){
        Vote vote=new Vote();
        vote.createVote(createVoteRequest.getTitle(), createVoteRequest.getMemo(), createVoteRequest.getIsAnonymous(), createVoteRequest.getIsMultiple());
        Team team = teamValidateService.validateTeam(teamId);
        vote.setTeam(team);
        vote.setUser(userRepository.findById(SecurityUtils.getLoggedInUser().getUserId()).orElseThrow(()->new NotFoundEmailException()));
        return vote;
    }

}
