package com.modagbul.BE.domain.team_member.domain.repository;

import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.domain.team_member.domain.entity.TeamMember;
import com.modagbul.BE.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long>, TeamMemberRepositoryCustom {
    Optional<TeamMember> findByTeamAndUser(Team team, User user);

    @Query(value = "select tm.user from TeamMember tm where tm.team.teamId = :teamId")
    Optional<List<User>> findUserListByTeamId(Long teamId);

}
