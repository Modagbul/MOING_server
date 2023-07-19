package com.modagbul.BE.domain.team.domain.repository;


import com.modagbul.BE.domain.team.application.dto.res.TeamResponse.GetTeamResponse;

public interface TeamRepositoryCustom {
    GetTeamResponse findTeamByUserId(Long userId);

    Long findLeaderIdByTeamId(Long teamId);

}
