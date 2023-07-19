package com.modagbul.BE.domain.team.application.service;

import com.modagbul.BE.domain.team.application.dto.req.TeamRequest;
import com.modagbul.BE.domain.team.application.dto.res.TeamResponse.CreateTeamResponse;
import com.modagbul.BE.domain.team.application.mapper.TeamMapper;
import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.domain.team.domain.service.TeamSaveService;
import com.modagbul.BE.domain.team_member.application.service.TeamMemberJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamCreateService {
    private final TeamMapper teamMapper;
    private final TeamCodeService teamCodeService;
    private final TeamSaveService teamSaveService;
    private final TeamMemberJoinService teamMemberJoinService;
    private final TeamUpdateService teamUpdateService;


    public CreateTeamResponse createTeam(TeamRequest.CreateTeamRequest createTeamRequest) {
        Team team = teamMapper.toEntity(createTeamRequest);
        String code = teamCodeService.generateCode();
        team.setInvitationCode(code);
        //추후에 승인 절차 만들 예정
        teamUpdateService.approveTeam(team);
        teamSaveService.saveTeam(team);

        teamMemberJoinService.addTeamMember(team);
        return new CreateTeamResponse(team.getTeamId());
    }
}
