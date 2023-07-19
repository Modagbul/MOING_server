package com.modagbul.BE.domain.team_member.application.service;

import com.modagbul.BE.domain.team_member.domain.entity.TeamMember;
import com.modagbul.BE.domain.team_member.domain.service.TeamMemberQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamMemberInfoService {
    private final TeamMemberQueryService teamMemberQueryService;

    public Optional<List<String>> getTeamMemberFcmToken(Long teamId, Long userId) {
        return teamMemberQueryService.getFcmTokenByTeamId(teamId, userId);
    }

    public List<TeamMember> getTeamMember(Long teamId){
        return teamMemberQueryService.getTeamMemberByTeamId(teamId);
    }
}
