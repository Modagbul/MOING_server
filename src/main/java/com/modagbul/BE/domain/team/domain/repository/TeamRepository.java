package com.modagbul.BE.domain.team.domain.repository;

import com.modagbul.BE.domain.team.domain.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long>, TeamRepositoryCustom {
    Optional<Team> findByInvitationCode(String invitationCode);
    Optional<Team> findByName(String teamName);

}
