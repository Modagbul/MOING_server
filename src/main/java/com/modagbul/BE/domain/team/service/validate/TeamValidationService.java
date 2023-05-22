package com.modagbul.BE.domain.team.service.validate;

import com.modagbul.BE.domain.team.dto.TeamDto;
import com.modagbul.BE.domain.team.dto.TeamDto.CheckTeamNameResponse;
import com.modagbul.BE.domain.team.entity.Team;

public interface TeamValidationService {

    Team validateTeam(Long teamId);

    void checkLeader(Team team);
    CheckTeamNameResponse checkTeamName(String teamName);

}
