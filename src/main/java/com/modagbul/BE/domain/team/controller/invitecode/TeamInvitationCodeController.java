package com.modagbul.BE.domain.team.controller.invitecode;

import com.modagbul.BE.domain.team.dto.TeamDto;
import com.modagbul.BE.domain.team.service.invitecode.TeamInvitationService;
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

import static com.modagbul.BE.domain.team.constant.TeamConstant.ETeamResponseMessage.GET_INVITE_CODE_SUCCESS;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/team")
@Api(tags = "Team API")
public class TeamInvitationCodeController {

    private final TeamInvitationService teamInvitationService;

    @ApiOperation(value="소모임 초대 코드 조회", notes="소모임 초대코드를 조회합니다")
    @GetMapping("/{teamId}/invite-code")
    public ResponseEntity<ResponseDto<TeamDto.GetInviteCodeResponse>> getInviteTeam(@PathVariable Long teamId){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), GET_INVITE_CODE_SUCCESS.getMessage(), this.teamInvitationService.getInviteCode(teamId)));
    }
}
