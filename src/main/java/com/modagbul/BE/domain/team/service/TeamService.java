package com.modagbul.BE.domain.team.service;

import com.modagbul.BE.domain.team.dto.TeamDto.CreateTeamRequest;
import com.modagbul.BE.domain.team.dto.TeamDto.CreateTeamResponse;

public interface TeamService {
    CreateTeamResponse createTeam(CreateTeamRequest createTeamRequest);
}
