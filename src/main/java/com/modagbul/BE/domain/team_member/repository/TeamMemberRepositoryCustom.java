package com.modagbul.BE.domain.team_member.repository;

import com.modagbul.BE.domain.team_member.entity.TeamMember;

import java.util.List;
import java.util.Optional;

public interface TeamMemberRepositoryCustom {
    List<TeamMember> findByTeamId(Long teamId);
    Optional<List<String>> getFcmTokensByTeamId(Long teamId, Long userId);
}
