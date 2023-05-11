package com.modagbul.BE.domain.team.repository;

import com.modagbul.BE.domain.team.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long>, TeamRepositoryCustom {
    Optional<Team> findByInvitationCode(String invitaionCode);
    Optional<Team> findByName(String teamName);

}
