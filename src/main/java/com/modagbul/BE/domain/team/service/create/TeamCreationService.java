package com.modagbul.BE.domain.team.service.create;

import com.modagbul.BE.domain.team.dto.TeamDto;
import com.modagbul.BE.domain.team.dto.TeamDto.CreateTeamResponse;

public interface TeamCreationService {
    CreateTeamResponse createTeam(TeamDto.CreateTeamRequest createTeamRequest);
}
