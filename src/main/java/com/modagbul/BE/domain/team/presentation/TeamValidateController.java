package com.modagbul.BE.domain.team.presentation;

import com.modagbul.BE.domain.team.application.dto.res.TeamResponse.CheckTeamNameResponse;
import com.modagbul.BE.domain.team.application.service.TeamValidateService;
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

import static com.modagbul.BE.domain.team.presentation.constant.ETeamResponseMessage.CHECK_TEAM_NAME_SUCCESS;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/team")
@Api(tags = "Team API")
public class TeamValidateController {

    private final TeamValidateService teamValidateService;

    @ApiOperation(value = "소모임 이름 중복 검사", notes = "소모임 이름 중복 검사를 합니다")
    @GetMapping("/teamname/{teamName}/available")
    public ResponseEntity<ResponseDto<CheckTeamNameResponse>> checkTeamName(@PathVariable String teamName) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), CHECK_TEAM_NAME_SUCCESS.getMessage(), teamValidateService.checkTeamName(teamName)));
    }
}
