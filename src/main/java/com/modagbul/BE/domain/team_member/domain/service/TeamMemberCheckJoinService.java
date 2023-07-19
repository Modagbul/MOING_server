package com.modagbul.BE.domain.team_member.domain.service;

import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.domain.team_member.domain.repository.TeamMemberRepository;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@DomainService
@Transactional
@RequiredArgsConstructor
public class TeamMemberCheckJoinService {
    private final TeamMemberRepository teamMemberRepository;

    public boolean isAlreadyJoin(Team team, User user){
        if(teamMemberRepository.findByTeamAndUser(team, user).isPresent())
            return true;
        else return false;
    }
}
