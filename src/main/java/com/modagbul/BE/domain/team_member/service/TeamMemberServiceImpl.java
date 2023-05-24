package com.modagbul.BE.domain.team_member.service;

import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.team.exception.AlreadyJoinException;
import com.modagbul.BE.domain.team.exception.AuthenticationException;
import com.modagbul.BE.domain.team.repository.TeamRepository;
import com.modagbul.BE.domain.team_member.dto.TeamMemberDto.JoinTeamRequest;
import com.modagbul.BE.domain.team_member.dto.TeamMemberDto.JoinTeamResponse;
import com.modagbul.BE.domain.team_member.entity.TeamMember;
import com.modagbul.BE.domain.team_member.repository.TeamMemberRepository;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamMemberServiceImpl implements TeamMemberService {

    private final UserRepository userRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final TeamRepository teamRepository;

    @Override
    public JoinTeamResponse joinTeam(JoinTeamRequest joinTeamRequest) {
        Team team = teamRepository.findByInvitationCode(joinTeamRequest.getInvitationCode())
                .orElseThrow(AuthenticationException::new);
        this.addTeamMember(team);
        return new JoinTeamResponse(team.getTeamId());
    }

    @Override
    public void addTeamMember(Team team) {

        TeamMember teamMember = new TeamMember();
        User user = userRepository.findById(SecurityUtils.getLoggedInUser().getUserId()).orElseThrow(NotFoundEmailException::new);

        //1. 중복 검사
        if (teamMemberRepository.findByTeamAndUser(team, user).isPresent()) {
            throw new AlreadyJoinException();
        }

        //2. 저장
        teamMember.setTeam(team);
        teamMember.setUser(user);
        teamMemberRepository.save(teamMember);
    }

    @Override
    public List<String> getTeamMemberFcmToken(Long teamId, Long userId) {
        return teamMemberRepository.getFcmTokensByTeamId(teamId, userId).orElseThrow();
    }
}
