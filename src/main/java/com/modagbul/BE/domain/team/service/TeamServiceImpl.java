package com.modagbul.BE.domain.team.service;

import com.modagbul.BE.domain.team.dto.TeamDto.CreateTeamRequest;
import com.modagbul.BE.domain.team.dto.TeamDto.CreateTeamResponse;
import com.modagbul.BE.domain.team.dto.TeamMapper;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.team.repository.TeamRepository;
import com.modagbul.BE.domain.teammember.entity.TeamMember;
import com.modagbul.BE.domain.teammember.repository.TeamMemberRepository;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamMapper teamMapper;
    private final InvitationCodeGenerator invitationCodeGenerator;

    private final TeamRepository teamRepository;

    private final TeamMemberRepository teamUserRepository;
    @Override
    public CreateTeamResponse createTeam(CreateTeamRequest createTeamRequest) {
        Team team= teamMapper.toEntity(createTeamRequest);
        String code=invitationCodeGenerator.generateCode();
        team.setInvitationCode(code);
        team.setApprovalStatus();
        teamRepository.save(team);
        this.addTeamMember(team);
        return new CreateTeamResponse(code);
    }

    private void addTeamMember(Team team){
        TeamMember teamMember=new TeamMember();
        teamMember.setTeam(team);
        teamMember.setUser(SecurityUtils.getLoggedInUser());
        teamUserRepository.save(teamMember);
    }
}
