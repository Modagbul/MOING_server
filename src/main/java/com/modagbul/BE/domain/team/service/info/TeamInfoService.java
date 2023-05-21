package com.modagbul.BE.domain.team.service.info;

import com.modagbul.BE.domain.team.dto.TeamDto;
import com.modagbul.BE.domain.team.entity.Team;

public interface TeamInfoService {
    TeamDto.GetTeamInfo getTeamInfo(Long teamId);
    void updateTeam(Long teamId, TeamDto.UpdateTeamRequest updateTeamRequest);
    TeamDto.GetTeamResponse getTeam();
    TeamDto.GetProfileResponse getTeamProfile(Long teamId);
    void approveTeam(Team team);
}
