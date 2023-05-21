package com.modagbul.BE.domain.team.service;

import com.modagbul.BE.domain.team.dto.TeamDto;
import com.modagbul.BE.domain.team.dto.TeamMapper;
import com.modagbul.BE.domain.team.repository.TeamRepository;
import com.modagbul.BE.domain.team.service.invitecode.TeamInvitationService;
import com.modagbul.BE.domain.team_member.repository.TeamMemberRepository;
import com.modagbul.BE.domain.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static com.modagbul.BE.domain.team.constant.TeamConstant.Category.ETC;

public class TeamServiceImplTest {
    @Mock
    TeamMapper teamMapper;
    @Mock
    TeamInvitationService invitationCodeGenerator;
    @Mock
    TeamRepository teamRepository;
    @Mock
    TeamMemberRepository teamMemberRepository;
    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
       TeamDto.CreateTeamRequest createTeamRequest=new TeamDto.CreateTeamRequest(ETC.getValue(), "소모임", 5, "2023-05-23", 3, "소모임 소개글", "소모임 각오", "소모임 대표사진 url");

    }
    @Test
    void 팀을_생성한다() {

    }

    @Test
    void authenticateCode() {
    }

    @Test
    void getTeamInfo() {
    }

    @Test
    void updateTeam() {
    }

    @Test
    void validateTeam() {
    }

    @Test
    void getTeam() {
    }

    @Test
    void checkTeamName() {
    }

    @Test
    void getTeamProfile() {
    }

    @Test
    void getInviteCode() {
    }
}
