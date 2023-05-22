package com.modagbul.BE.domain.team.controller.info;

import com.modagbul.BE.domain.team.dto.TeamDto;
import com.modagbul.BE.domain.team.service.info.TeamInfoService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.modagbul.BE.domain.team.constant.TeamConstant.ETeamResponseMessage.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/team")
@Api(tags = "Team API")
public class TeamInfoController {

    private final TeamInfoService teamInfoService;

    @ApiOperation(value="소모임 정보 조회", notes="소모임 수정을 위한 정보를 조회합니다")
    @GetMapping("/{teamId}")
    public ResponseEntity<ResponseDto<TeamDto.GetTeamInfo>> getTeamInfo(@PathVariable Long teamId){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), GET_TEAM_INFO_SUCCESS.getMessage(),this.teamInfoService.getTeamInfo(teamId)));
    }

    @ApiOperation(value="소모임 목록 정보 조회", notes="홈 화면에서 소모임 목록을 조회합니다")
    @GetMapping
    public ResponseEntity<ResponseDto<TeamDto.GetTeamResponse>> getTeam(){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), GET_TEAM_SUCCESS.getMessage(), teamInfoService.getTeam()));
    }

    @ApiOperation(value="목표보드 프로필 조회", notes="목표보드 프로필을 조회합니다")
    @GetMapping("/{teamId}/goal-board")
    public ResponseEntity<ResponseDto<TeamDto.GetProfileResponse>> getProfile(@PathVariable Long teamId){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), GET_GOAL_BOARD_PROFILE_SUCCESS.getMessage(), teamInfoService.getTeamProfile(teamId)));
    }
}
