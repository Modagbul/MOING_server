package com.modagbul.BE.domain.team.service.info;

import com.modagbul.BE.domain.team.dto.TeamDto;

public interface TeamInfoService {

    TeamDto.GetTeamInfo getTeamInfo(Long teamId);

    TeamDto.GetTeamResponse getTeam();

    TeamDto.GetProfileResponse getTeamProfile(Long teamId);
}

