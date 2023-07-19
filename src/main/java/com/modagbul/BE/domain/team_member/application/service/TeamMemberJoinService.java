package com.modagbul.BE.domain.team_member.application.service;

import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.domain.team.domain.service.TeamQueryService;
import com.modagbul.BE.domain.team.exception.AlreadyJoinException;
import com.modagbul.BE.domain.team_member.application.dto.req.TeamMemberRequest.JoinTeamRequest;
import com.modagbul.BE.domain.team_member.application.dto.res.TeamMemberResponse.JoinTeamResponse;
import com.modagbul.BE.domain.team_member.domain.entity.TeamMember;
import com.modagbul.BE.domain.team_member.domain.service.TeamMemberCheckJoinService;
import com.modagbul.BE.domain.team_member.domain.service.TeamMemberSaveService;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamMemberJoinService {

    private final UserRepository userRepository;
    private final TeamMemberSaveService teamMemberSaveService;
    private final TeamQueryService teamQueryService;
    private final TeamMemberCheckJoinService teamMemberCheckJoinService;


    public JoinTeamResponse joinTeam(JoinTeamRequest joinTeamRequest) {
        Team team = teamQueryService.getTeamByCode(joinTeamRequest);
        this.addTeamMember(team);
        return new JoinTeamResponse(team.getTeamId(), team.getProfileImg());
    }

    public void addTeamMember(Team team) {

        TeamMember teamMember = new TeamMember();
        User user = userRepository.findById(SecurityUtils.getLoggedInUser().getUserId()).orElseThrow(NotFoundEmailException::new);

        //1. 중복 검사
        if (teamMemberCheckJoinService.isAlreadyJoin(team, user)) {
            throw new AlreadyJoinException();
        }

        //2. 저장
        teamMember.setTeam(team);
        teamMember.setUser(user);
        teamMemberSaveService.saveTeamMember(teamMember);
    }
}