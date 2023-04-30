package com.modagbul.BE.domain.team.service;

import com.modagbul.BE.domain.team.dto.TeamDto;
import com.modagbul.BE.domain.team.dto.TeamDto.CreateTeamRequest;
import com.modagbul.BE.domain.team.dto.TeamDto.CreateTeamResponse;
import com.modagbul.BE.domain.team.dto.TeamDto.JoinTeamResponse;
import com.modagbul.BE.domain.team.dto.TeamMapper;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.team.exception.AlreadyJoinException;
import com.modagbul.BE.domain.team.exception.AuthenticationException;
import com.modagbul.BE.domain.team.repository.TeamRepository;
import com.modagbul.BE.domain.teammember.entity.TeamMember;
import com.modagbul.BE.domain.teammember.repository.TeamMemberRepository;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TeamServiceImpl implements TeamService {

    private final TeamMapper teamMapper;
    private final InvitationCodeGenerator invitationCodeGenerator;

    private final TeamRepository teamRepository;

    private final TeamMemberRepository teamMemberRepository;
    @Override
    public CreateTeamResponse createTeam(CreateTeamRequest createTeamRequest) {
        Team team= teamMapper.toEntity(createTeamRequest);
        String code=invitationCodeGenerator.generateCode();
        team.setInvitationCode(code);
        team.setApprovalStatus();
        teamRepository.save(team);

        this.addTeamMember(team);
        return new CreateTeamResponse(team.getTeamId(), code);
    }

    @Override
    public JoinTeamResponse authenticateCode(TeamDto.JoinTeamRequest joinTeamRequest){
        Team team=teamRepository.findByInvitationCode(joinTeamRequest.getInvitationCode())
                .orElseThrow(AuthenticationException::new);
        this.addTeamMember(team);
        return new JoinTeamResponse(team.getTeamId());
    }

    private void addTeamMember(Team team) {

        TeamMember teamMember=new TeamMember();
        User user=SecurityUtils.getLoggedInUser();

        //1. 중복 검사
        if(teamMemberRepository.findByTeamAndUser(team, user).isPresent()){
            throw new AlreadyJoinException();
        }

        //2. 저장
        teamMember.setTeam(team);
        teamMember.setUser(user);
        teamMemberRepository.save(teamMember);
    }
}
