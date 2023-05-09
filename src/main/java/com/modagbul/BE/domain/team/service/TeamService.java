package com.modagbul.BE.domain.team.service;

import com.modagbul.BE.domain.team.dto.TeamDto.*;
import com.modagbul.BE.domain.team.entity.Team;

import java.util.List;

public interface TeamService {
    CreateTeamResponse createTeam(CreateTeamRequest createTeamRequest);
    JoinTeamResponse authenticateCode(JoinTeamRequest joinTeamRequest);
    GetTeamInfo getTeamInfo(Long teamId);
    void updateTeam(Long teamId, UpdateTeamRequest updateTeamRequest);
    Team validateTeam(Long teamId);
    GetTeamResponse getTeam();
}
