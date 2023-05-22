package com.modagbul.BE.domain.team.service.manage;

import com.modagbul.BE.domain.team.dto.TeamDto;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.team.service.validate.TeamValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamManagementServiceImpl implements TeamManagementService{
    private final TeamValidationService teamValidationService;

    @Override
    public void updateTeam(Long teamId, TeamDto.UpdateTeamRequest updateTeamRequest) {
        Team team=teamValidationService.validateTeam(teamId);
        teamValidationService.checkLeader(team);
        team.updateTeam(updateTeamRequest.getName(), LocalDate.parse(updateTeamRequest.getEndDate()), updateTeamRequest.getProfileImg());
    }

    @Override
    public void approveTeam(Team team){
        team.setApprovalStatus();
    }
}
