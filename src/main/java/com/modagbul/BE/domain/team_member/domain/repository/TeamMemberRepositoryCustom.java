package com.modagbul.BE.domain.team_member.domain.repository;

import com.modagbul.BE.domain.team_member.domain.entity.TeamMember;

import java.util.List;
import java.util.Optional;

public interface TeamMemberRepositoryCustom {
    List<TeamMember> findByTeamId(Long teamId);
    Optional<List<String>> findFcmTokensByTeamId(Long teamId, Long userId);
}
