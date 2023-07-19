package com.modagbul.BE.domain.team.domain.service;

import com.modagbul.BE.domain.team.domain.repository.TeamRepository;
import com.modagbul.BE.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@DomainService
@RequiredArgsConstructor
@Transactional
public class TeamCheckDuplicateService {
    private final TeamRepository teamRepository;

    public boolean isDuplicatedCode(String encodedCode){
        if( teamRepository.findByInvitationCode(encodedCode).isPresent())
            return true;
        else return false;
    }

    public boolean isDuplicatedTeamName(String teamName){
        if(teamRepository.findByName(teamName).isPresent())
            return true;
        else return false;
    }
}
