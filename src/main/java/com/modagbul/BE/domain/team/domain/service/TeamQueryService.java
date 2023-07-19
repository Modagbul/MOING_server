package com.modagbul.BE.domain.team.domain.service;

import com.modagbul.BE.domain.team.application.dto.res.TeamResponse.GetTeamResponse;
import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.domain.team.domain.repository.TeamRepository;
import com.modagbul.BE.domain.team.exception.AuthenticationException;
import com.modagbul.BE.domain.team.exception.NotFoundTeamIdException;
import com.modagbul.BE.domain.team_member.application.dto.req.TeamMemberRequest.JoinTeamRequest;
import com.modagbul.BE.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@DomainService
@Transactional
@RequiredArgsConstructor
public class TeamQueryService {
    private final TeamRepository teamRepository;

    public GetTeamResponse getTeamByUserId(Long userId) {
        return teamRepository.findTeamByUserId(userId);
    }

    public Long getLeaderIdByTeamId(Long teamId) {
        return teamRepository.findLeaderIdByTeamId(teamId);
    }

    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new NotFoundTeamIdException());
    }

    public Team getTeamByCode(JoinTeamRequest joinTeamRequest) {
        return teamRepository.findByInvitationCode(joinTeamRequest.getInvitationCode())
                .orElseThrow(AuthenticationException::new);
    }
}
