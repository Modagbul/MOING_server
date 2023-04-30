package com.modagbul.BE.domain.team.service;

import com.modagbul.BE.domain.team.dto.TeamDto;
import com.modagbul.BE.domain.team.dto.TeamDto.CreateTeamRequest;
import com.modagbul.BE.domain.team.dto.TeamDto.CreateTeamResponse;
import com.modagbul.BE.domain.team.dto.TeamDto.JoinTeamRequest;
import com.modagbul.BE.domain.team.entity.Team;

import java.rmi.AlreadyBoundException;

public interface TeamService {
    CreateTeamResponse createTeam(CreateTeamRequest createTeamRequest);
    void authenticateCode(JoinTeamRequest joinTeamRequest);
}
