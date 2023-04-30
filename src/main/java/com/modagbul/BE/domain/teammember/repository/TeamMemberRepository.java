package com.modagbul.BE.domain.teammember.repository;

import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.teammember.entity.TeamMember;
import com.modagbul.BE.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    Optional<TeamMember> findByTeamAndUser(Team team, User user);
}
