package com.modagbul.BE.domain.team.controller;

import com.modagbul.BE.domain.team.dto.TeamDto.*;
import com.modagbul.BE.domain.team.service.TeamService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.modagbul.BE.domain.team.constant.TeamConstant.ETeamResponseMessage.*;

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
    @PostMapping("/join")
    public ResponseEntity<ResponseDto<JoinTeamResponse>> joinTeam(@Valid @RequestBody JoinTeamRequest joinTeamRequest)  {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), JOIN_TEAM_SUCCESS.getMessage(), this.teamService.authenticateCode(joinTeamRequest)));
    }

    @ApiOperation(value="소모임 정보 조회", notes="소모임 수정을 위한 정보를 조회합니다")
    @GetMapping("/{teamId}")
    public ResponseEntity<ResponseDto<GetTeamInfo>> getTeamInfo(@PathVariable Long teamId){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), GET_TEAM_INFO_SUCCESS.getMessage(),this.teamService.getTeamInfo(teamId)));
    }

    @ApiOperation(value="소모임 정보 수정", notes="소모임 정보를 수정합니다")
    @PutMapping("/{teamId}")
    public ResponseEntity<ResponseDto> updateTeam(@Valid @RequestBody UpdateTeamRequest updateTeamRequest, @PathVariable Long teamId){
        this.teamService.updateTeam(teamId, updateTeamRequest);
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), UPDATE_TEAM_SUCCESS.getMessage()));
    }

    //진행 중인 소모임 조회
    @ApiOperation(value="소모임 정보 조회", notes="홈 화면에서 소모임 정보를 조회합니다")
    @GetMapping
    public ResponseEntity<ResponseDto<GetTeamResponse>> getTeam(){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), GET_TEAM_SUCCESS.getMessage(), teamService.getTeam()));
    }

    //소모임 이름 중복 체크
}
