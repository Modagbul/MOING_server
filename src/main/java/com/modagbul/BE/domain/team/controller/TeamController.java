package com.modagbul.BE.domain.team.controller;

import com.modagbul.BE.domain.team.dto.TeamDto.CreateTeamRequest;
import com.modagbul.BE.domain.team.dto.TeamDto.CreateTeamResponse;
import com.modagbul.BE.domain.team.dto.TeamDto.JoinTeamRequest;
import com.modagbul.BE.domain.team.dto.TeamDto.JoinTeamResponse;
import com.modagbul.BE.domain.team.service.TeamService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.modagbul.BE.domain.team.constant.TeamConstant.ETeamResponseMessage.CREATE_TEAM_SUCCESS;
import static com.modagbul.BE.domain.team.constant.TeamConstant.ETeamResponseMessage.JOIN_TEAM_SUCCESS;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/team")
@Api(tags = "Team API")
public class TeamController {
    private TeamService teamService;

    @ApiOperation(value = "소모임 생성", notes = "소모임을 생성합니다.")
    @PostMapping
    public ResponseEntity<ResponseDto<CreateTeamResponse>> createTeam(@Valid @RequestBody CreateTeamRequest createTeamRequest){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), CREATE_TEAM_SUCCESS.getMessage(), this.teamService.createTeam(createTeamRequest)));
    }

    @ApiOperation(value = "소모임 참여 코드 인증", notes = "소모임 참여 코드를 인증합니다.")
    @PostMapping("/auth")
    public ResponseEntity<ResponseDto<JoinTeamResponse>> joinTeam(@Valid @RequestBody JoinTeamRequest joinTeamRequest)  {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), JOIN_TEAM_SUCCESS.getMessage(), this.teamService.authenticateCode(joinTeamRequest)));
    }
}
