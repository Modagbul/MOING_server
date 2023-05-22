package com.modagbul.BE.domain.team.service.info;

import com.modagbul.BE.domain.team.dto.TeamDto;
import com.modagbul.BE.domain.team.dto.TeamDto.GetTeamResponse;
import com.modagbul.BE.domain.team.dto.TeamMapper;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.team.repository.TeamRepository;
import com.modagbul.BE.domain.team.service.validate.TeamValidationService;
import com.modagbul.BE.domain.user.service.validate.UserValidationService;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamInfoServiceImpl implements TeamInfoService{

    private final TeamValidationService teamValidationService;
    private final TeamMapper teamMapper;
    private final TeamRepository teamRepository;
    private final UserValidationService userValidationService;

    @Override
    public TeamDto.GetTeamInfo getTeamInfo(Long teamId) {
        Team team=teamValidationService.validateTeam(teamId);
        teamValidationService.checkLeader(team);
        return teamMapper.toGetTeamInfo(team);
    }

    @Override
    public GetTeamResponse getTeam() {
        GetTeamResponse getTeamResponse=teamRepository.getTeam(SecurityUtils.getLoggedInUser().getUserId());
        String userNickName=userValidationService.validateEmail(SecurityUtils.getLoggedInUser().getEmail()).getNickName();
        getTeamResponse.setUserNickName(userNickName);
        return getTeamResponse;
    }

    @Override
    public TeamDto.GetProfileResponse getTeamProfile(Long teamId) {
        Team team=teamValidationService.validateTeam(teamId);
        return new TeamDto.GetProfileResponse(team);
    }


}
