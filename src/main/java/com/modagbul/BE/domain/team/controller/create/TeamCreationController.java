package com.modagbul.BE.domain.team.controller.create;

import com.modagbul.BE.domain.team.dto.TeamDto;
import com.modagbul.BE.domain.team.service.create.TeamCreationService;
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

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/team")
@Api(tags = "Team API")
public class TeamCreationController {

    private final TeamCreationService teamCreationService;
    @ApiOperation(value = "소모임 생성", notes = "소모임을 생성합니다.")
    @PostMapping
    public ResponseEntity<ResponseDto<TeamDto.CreateTeamResponse>> createTeam(@Valid @RequestBody TeamDto.CreateTeamRequest createTeamRequest){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), CREATE_TEAM_SUCCESS.getMessage(), this.teamCreationService.createTeam(createTeamRequest)));
    }
}
