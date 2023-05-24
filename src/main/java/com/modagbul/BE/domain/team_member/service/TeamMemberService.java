package com.modagbul.BE.domain.team_member.service;


import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.team_member.dto.TeamMemberDto.JoinTeamRequest;
import com.modagbul.BE.domain.team_member.dto.TeamMemberDto.JoinTeamResponse;

import java.util.List;

public interface TeamMemberService {
    JoinTeamResponse joinTeam(JoinTeamRequest joinTeamRequest);
    void addTeamMember(Team team);
    List<String> getTeamMemberFcmToken(Long teamId, Long userId);
}
