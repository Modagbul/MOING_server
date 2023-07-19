package com.modagbul.BE.domain.team.application.service;

import com.modagbul.BE.domain.team.application.dto.res.TeamResponse.GetProfileResponse;
import com.modagbul.BE.domain.team.application.dto.res.TeamResponse.GetTeamInfo;
import com.modagbul.BE.domain.team.application.dto.res.TeamResponse.GetTeamResponse;
import com.modagbul.BE.domain.team.application.mapper.TeamMapper;
import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.domain.team.domain.service.TeamQueryService;
import com.modagbul.BE.domain.team.exception.AuthenticationException;
import com.modagbul.BE.domain.team_member.application.dto.req.TeamMemberRequest;
import com.modagbul.BE.domain.user.service.validate.UserValidationService;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamInfoService {
    private final TeamValidateService teamValidateService;
    private final TeamMapper teamMapper;
    private final TeamQueryService teamQueryService;
    private final UserValidationService userValidationService;


    public GetTeamInfo getTeamInfo(Long teamId) {
        Team team = teamValidateService.validateTeam(teamId);
        teamValidateService.checkLeader(team);
        return teamMapper.toGetTeamInfo(team);
    }


    public GetTeamResponse getTeam() {
        GetTeamResponse getTeamResponse = teamQueryService.getTeamByUserId(SecurityUtils.getLoggedInUser().getUserId());
        String userNickName = userValidationService.validateEmail(SecurityUtils.getLoggedInUser().getEmail()).getNickName();
        getTeamResponse.setUserNickName(userNickName);
        return getTeamResponse;
    }


    public GetProfileResponse getTeamProfile(Long teamId) {
        Team team = teamValidateService.validateTeam(teamId);
        return new GetProfileResponse(team);
    }

}
