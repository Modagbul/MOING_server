package com.modagbul.BE.domain.team.application.service;

import com.modagbul.BE.domain.team.application.dto.res.TeamResponse.CheckTeamNameResponse;
import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.domain.team.domain.service.TeamCheckDuplicateService;
import com.modagbul.BE.domain.team.domain.service.TeamQueryService;
import com.modagbul.BE.domain.team.exception.AccessException;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.modagbul.BE.domain.team.application.service.constatnt.TeamServiceMessage.EXISTED_TEAMNAME;
import static com.modagbul.BE.domain.team.application.service.constatnt.TeamServiceMessage.VALID_TEAMNAME;


@Service
@Transactional
@RequiredArgsConstructor
public class TeamValidateService {

    private final TeamQueryService teamQueryService;
    private final TeamCheckDuplicateService teamCheckDuplicateService;


    public Team validateTeam(Long teamId) {
        return teamQueryService.getTeamById(teamId);
    }


    public void checkLeader(Team team) {
        if (team.getLeaderId() != SecurityUtils.getLoggedInUser().getUserId())
            throw new AccessException();
    }


    public CheckTeamNameResponse checkTeamName(String teamName) {
        if (teamCheckDuplicateService.isDuplicatedTeamName(teamName)) {
            return new CheckTeamNameResponse(EXISTED_TEAMNAME.getValue());
        } else {
            return new CheckTeamNameResponse(VALID_TEAMNAME.getValue());
        }
    }
}
