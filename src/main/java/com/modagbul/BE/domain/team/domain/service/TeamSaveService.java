package com.modagbul.BE.domain.team.domain.service;

import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.domain.team.domain.repository.TeamRepository;
import com.modagbul.BE.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@DomainService
@RequiredArgsConstructor
@Transactional
public class TeamSaveService {
    private final TeamRepository teamRepository;

    public void saveTeam(Team team){
        teamRepository.save(team);
    }
}
