package com.modagbul.BE.domain.team.service.manage;

import com.modagbul.BE.domain.team.dto.TeamDto;
import com.modagbul.BE.domain.team.entity.Team;

public interface TeamManagementService {
    void updateTeam(Long teamId, TeamDto.UpdateTeamRequest updateTeamRequest);
    void approveTeam(Team team);
}
