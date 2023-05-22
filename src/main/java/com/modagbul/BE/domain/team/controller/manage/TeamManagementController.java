package com.modagbul.BE.domain.team.controller.manage;

import com.modagbul.BE.domain.team.dto.TeamDto;
import com.modagbul.BE.domain.team.service.info.TeamInfoService;
import com.modagbul.BE.domain.team.service.manage.TeamManagementServiceImpl;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.modagbul.BE.domain.team.constant.TeamConstant.ETeamResponseMessage.UPDATE_TEAM_SUCCESS;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/team")
@Api(tags = "Team API")
public class TeamManagementController {

    private final TeamManagementServiceImpl teamManagementService;

    @ApiOperation(value="소모임 정보 수정", notes="소모임 정보를 수정합니다")
    @PutMapping("/{teamId}")
    public ResponseEntity<ResponseDto> updateTeam(@Valid @RequestBody TeamDto.UpdateTeamRequest updateTeamRequest, @PathVariable Long teamId){
        this.teamManagementService.updateTeam(teamId, updateTeamRequest);
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), UPDATE_TEAM_SUCCESS.getMessage()));
    }
}
