package com.modagbul.BE.domain.team.application.service;

import com.modagbul.BE.domain.team.application.dto.res.TeamResponse.GetInviteCodeResponse;
import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.domain.team.domain.service.TeamCheckDuplicateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamCodeService {
    private final TeamCheckDuplicateService teamCheckDuplicateService;
    private final TeamValidateService teamValidateService;


    public String generateCode() {
        String code;
        do {
            code = UUID.randomUUID().toString();
        } while (teamCheckDuplicateService.isDuplicatedCode(code));
        return code;
    }


    public GetInviteCodeResponse getInviteCode(Long teamId) {
        Team team = teamValidateService.validateTeam(teamId);
        teamValidateService.checkLeader(team);
        return new GetInviteCodeResponse(team.getInvitationCode());
    }
}
