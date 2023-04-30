package com.modagbul.BE.domain.team.service;

import com.modagbul.BE.domain.team.dto.TeamDto.CreateTeamRequest;
import com.modagbul.BE.domain.team.dto.TeamDto.CreateTeamResponse;
import com.modagbul.BE.domain.team.dto.TeamDto.JoinTeamRequest;
import com.modagbul.BE.domain.team.dto.TeamDto.JoinTeamResponse;

public interface TeamService {
    CreateTeamResponse createTeam(CreateTeamRequest createTeamRequest);
    JoinTeamResponse authenticateCode(JoinTeamRequest joinTeamRequest);
}
