package com.modagbul.BE.domain.team.presentation;

import com.modagbul.BE.domain.team.application.dto.req.TeamRequest.UpdateTeamRequest;
import com.modagbul.BE.domain.team.application.service.TeamUpdateService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.modagbul.BE.domain.team.presentation.constant.ETeamResponseMessage.UPDATE_TEAM_SUCCESS;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/team")
@Api(tags = "Team API")
public class TeamUpdateController {

    private final TeamUpdateService teamUpdateService;

    @ApiOperation(value = "소모임 정보 수정", notes = "소모임 정보를 수정합니다")
    @PutMapping("/{teamId}")
    public ResponseEntity<ResponseDto> updateTeam(@Valid @RequestBody UpdateTeamRequest updateTeamRequest, @PathVariable Long teamId) {
        this.teamUpdateService.updateTeam(teamId, updateTeamRequest);
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), UPDATE_TEAM_SUCCESS.getMessage()));
    }
}
