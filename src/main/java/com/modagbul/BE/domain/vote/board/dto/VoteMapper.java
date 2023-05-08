package com.modagbul.BE.domain.vote.board.dto;

import com.modagbul.BE.domain.notice.board.dto.NoticeDto;
import com.modagbul.BE.domain.notice.board.entity.Notice;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.team.exception.NotFoundTeamIdException;
import com.modagbul.BE.domain.team.repository.TeamRepository;
import com.modagbul.BE.domain.team.service.TeamService;
import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.domain.vote.board.dto.VoteDto.CreateVoteRequest;
import com.modagbul.BE.domain.vote.board.dto.VoteDto.GetVoteDetailsResponse;
import com.modagbul.BE.domain.vote.board.dto.VoteDto.VoteChoice;
import com.modagbul.BE.domain.vote.board.entity.Vote;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class VoteMapper {

    private final TeamService teamService;

    private final UserRepository userRepository;

    public Vote toEntity(Long teamId, CreateVoteRequest createVoteRequest){
        Vote vote=new Vote();
        vote.createVote(createVoteRequest.getTitle(), createVoteRequest.getMemo(), createVoteRequest.isAnonymous(), createVoteRequest.isMultiple());
        Team team = teamService.validateTeam(teamId);
        vote.setTeam(team);
        vote.setUser(userRepository.findById(SecurityUtils.getLoggedInUser().getUserId()).orElseThrow(()->new NotFoundEmailException()));
        return vote;
    }

}
