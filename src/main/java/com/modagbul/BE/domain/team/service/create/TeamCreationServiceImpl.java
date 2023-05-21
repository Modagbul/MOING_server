package com.modagbul.BE.domain.team.service.create;

import com.modagbul.BE.domain.team.dto.TeamDto;
import com.modagbul.BE.domain.team.dto.TeamMapper;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.team.repository.TeamRepository;
import com.modagbul.BE.domain.team.service.info.TeamInfoService;
import com.modagbul.BE.domain.team.service.invitecode.TeamInvitationService;
import com.modagbul.BE.domain.team_member.service.TeamMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamCreationServiceImpl implements TeamCreationService{

    private final TeamMapper teamMapper;
    private final TeamInvitationService teamInvitationService;

    private final TeamRepository teamRepository;

    private final TeamMemberService teamMemberService;
    private final TeamInfoService teamInfoService;

    @Override
    public TeamDto.CreateTeamResponse createTeam(TeamDto.CreateTeamRequest createTeamRequest) {
        Team team= teamMapper.toEntity(createTeamRequest);
        String code=teamInvitationService.generateCode();
        team.setInvitationCode(code);
        //추후에 승인 절차 만들 예정
        teamInfoService.approveTeam(team);
        teamRepository.save(team);

        this.teamMemberService.addTeamMember(team);
        return new TeamDto.CreateTeamResponse(team.getTeamId());
    }

}
