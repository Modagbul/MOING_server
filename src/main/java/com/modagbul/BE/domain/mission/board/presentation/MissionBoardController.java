package com.modagbul.BE.domain.mission.board.presentation;

import com.modagbul.BE.domain.mission.board.application.dto.MissionBoardDto;
import com.modagbul.BE.domain.mission.board.application.dto.MissionRateDto;
import com.modagbul.BE.domain.mission.board.application.service.MissionBoardService;
import com.modagbul.BE.domain.mission.main.domain.service.MissionQueryService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.modagbul.BE.domain.mission.main.application.constant.MissionConstant.MissionResponseMessage.STATUS_MISSION_SUCCESS;

@RestController
@RequiredArgsConstructor
@Api(tags = "Mission CRUD API")
@RequestMapping("/api/v1/{teamId}/missions")
public class MissionBoardController {

    private final MissionBoardService missionBoardService;
    private final MissionQueryService missionQueryService;



    @ApiOperation(value = "개인별 미션 인증 비율", notes = "개인별 미션 인증 비율을 조회합니다..")
    @GetMapping("/graph")
    public ResponseEntity<ResponseDto<MissionRateDto>> graph(@PathVariable Long teamId){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),STATUS_MISSION_SUCCESS.getMessage(),new MissionRateDto(missionBoardService.getPersonalRateForGraph(teamId),missionBoardService.getTeamPercentForGraph(teamId).getPercent())));
    }

    @ApiOperation(value = "팀별 미션 인증 비율", notes = "팀별 미션 인증 비율을 조회합니다..")
    @GetMapping("/teamRate")
    public ResponseEntity<ResponseDto<MissionBoardDto>> teamRate(@PathVariable Long teamId){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),STATUS_MISSION_SUCCESS.getMessage(),missionBoardService.getTeamPercentForGraph(teamId)));
    }
    @ApiOperation(value = "개인별 미션 인증 비율", notes = "개인별 미션 인증 비율을 조회합니다..")
    @GetMapping("/personalRate")
    public ResponseEntity<ResponseDto<Long>> personalRate(@PathVariable Long teamId){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),STATUS_MISSION_SUCCESS.getMessage(),missionBoardService.getPersonalRateForGraph(teamId)));
    }

    @GetMapping("/isLeader")
    public ResponseEntity<ResponseDto<Boolean>> isLeader(@PathVariable Long teamId){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),STATUS_MISSION_SUCCESS.getMessage(),missionQueryService.isLeader(teamId)));
    }



}
