package com.modagbul.BE.domain.teammember.repository;

import com.modagbul.BE.domain.teammember.entity.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
}
