package com.modagbul.BE.domain.team_member.repository;

import com.modagbul.BE.domain.team_member.entity.TeamMember;

import java.util.List;

public interface TeamMemberRepositoryCustom {
    List<TeamMember> findByTeamId(Long teamId);
    List<String> getFcmTokensByTeamId(Long teamId, Long userId);
}
