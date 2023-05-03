package com.modagbul.BE.domain.team.service;

import com.modagbul.BE.domain.team.dto.TeamDto.*;

public interface TeamService {
    CreateTeamResponse createTeam(CreateTeamRequest createTeamRequest);
    JoinTeamResponse authenticateCode(JoinTeamRequest joinTeamRequest);
    GetTeamInfo getTeamInfo(Long teamId);
    void updateTeam(Long teamId, UpdateTeamRequest updateTeamRequest);
}
