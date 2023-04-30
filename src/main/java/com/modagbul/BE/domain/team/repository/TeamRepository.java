package com.modagbul.BE.domain.team.repository;

import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByInvitationCode(String invitaionCode);

}
