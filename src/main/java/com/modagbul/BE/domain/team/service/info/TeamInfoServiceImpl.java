package com.modagbul.BE.domain.team.service.info;

import com.modagbul.BE.domain.team.dto.TeamDto;
import com.modagbul.BE.domain.team.dto.TeamMapper;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.team.repository.TeamRepository;
import com.modagbul.BE.domain.team.service.validate.TeamValidationService;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamInfoServiceImpl implements TeamInfoService{

    private final TeamValidationService teamValidationService;
    private final TeamMapper teamMapper;
    private final TeamRepository teamRepository;

    @Override
    public TeamDto.GetTeamInfo getTeamInfo(Long teamId) {
        Team team=teamValidationService.validateTeam(teamId);
        teamValidationService.checkLeader(team);
        return teamMapper.toGetTeamInfo(team);
    }

    @Override
    public void updateTeam(Long teamId, TeamDto.UpdateTeamRequest updateTeamRequest) {
        Team team=teamValidationService.validateTeam(teamId);
        teamValidationService.checkLeader(team);
        team.updateTeam(updateTeamRequest.getName(), LocalDate.parse(updateTeamRequest.getEndDate()), updateTeamRequest.getProfileImg());
    }

    @Override
    public TeamDto.GetTeamResponse getTeam() {
        return teamRepository.getTeam(SecurityUtils.getLoggedInUser().getUserId());
    }

    @Override
    public TeamDto.GetProfileResponse getTeamProfile(Long teamId) {
        Team team=teamValidationService.validateTeam(teamId);
        return new TeamDto.GetProfileResponse(team);
    }

    @Override
    public void approveTeam(Team team){
        team.setApprovalStatus();
    }
}
