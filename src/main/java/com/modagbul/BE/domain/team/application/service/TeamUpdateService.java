package com.modagbul.BE.domain.team.application.service;

import com.modagbul.BE.domain.team.application.dto.req.TeamRequest.UpdateTeamRequest;
import com.modagbul.BE.domain.team.domain.entity.Team;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamUpdateService {
    private final TeamValidateService teamValidateService;


    public void updateTeam(Long teamId, UpdateTeamRequest updateTeamRequest) {
        Team team = teamValidateService.validateTeam(teamId);
        teamValidateService.checkLeader(team);
        team.updateTeam(updateTeamRequest.getName(), LocalDate.parse(updateTeamRequest.getEndDate()), updateTeamRequest.getProfileImg());
    }

    public void approveTeam(Team team) {
        team.setApprovalStatus();
    }
}
