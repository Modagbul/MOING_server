package com.modagbul.BE.domain.team_member.domain.service;

import com.modagbul.BE.domain.team_member.domain.entity.TeamMember;
import com.modagbul.BE.domain.team_member.domain.repository.TeamMemberRepository;
import com.modagbul.BE.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@DomainService
@RequiredArgsConstructor
@Transactional
public class TeamMemberSaveService {
    private final TeamMemberRepository teamMemberRepository;

    public void saveTeamMember(TeamMember teamMember){
        teamMemberRepository.save(teamMember);
    }
}
