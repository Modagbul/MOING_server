package com.modagbul.BE.domain.team_member.controller;

import com.modagbul.BE.domain.team_member.dto.TeamMemberDto.JoinTeamRequest;
import com.modagbul.BE.domain.team_member.dto.TeamMemberDto.JoinTeamResponse;
import com.modagbul.BE.domain.team_member.service.TeamMemberService;
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

import static com.modagbul.BE.domain.team.constant.TeamConstant.ETeamResponseMessage.JOIN_TEAM_SUCCESS;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/team")
@Api(tags = "Team API")
public class TeamMemberController {

    private final TeamMemberService teamMemberService;

    @ApiOperation(value = "소모임 참여 코드 인증", notes = "소모임 참여 코드를 인증합니다.")
    @PostMapping("/join")
    public ResponseEntity<ResponseDto<JoinTeamResponse>> joinTeam(@Valid @RequestBody JoinTeamRequest joinTeamRequest)  {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), JOIN_TEAM_SUCCESS.getMessage(), this.teamMemberService.joinTeam(joinTeamRequest)));
    }
}
