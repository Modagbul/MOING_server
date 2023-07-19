package com.modagbul.BE.domain.team_member.domain.service;

import com.modagbul.BE.domain.team_member.domain.entity.TeamMember;
import com.modagbul.BE.domain.team_member.domain.repository.TeamMemberRepository;
import com.modagbul.BE.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@DomainService
@Transactional
@RequiredArgsConstructor
public class TeamMemberQueryService {

    private final TeamMemberRepository teamMemberRepository;

    public List<TeamMember> getTeamMemberByTeamId(Long teamId) {
        return teamMemberRepository.findByTeamId(teamId);
    }

    public Optional<List<String>> getFcmTokenByTeamId(Long teamId, Long userId) {
        return teamMemberRepository.findFcmTokensByTeamId(teamId, userId);
    }


}
