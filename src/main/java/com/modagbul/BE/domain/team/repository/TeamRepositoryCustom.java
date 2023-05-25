package com.modagbul.BE.domain.team.repository;

import com.modagbul.BE.domain.team.dto.TeamDto.GetTeamResponse;

public interface TeamRepositoryCustom {
    GetTeamResponse getTeam(Long userId);
    Long findLeaderIdByTeamId(Long teamId);

    }
