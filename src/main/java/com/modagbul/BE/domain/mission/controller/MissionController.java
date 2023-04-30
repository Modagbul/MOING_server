package com.modagbul.BE.domain.mission.controller;

import com.modagbul.BE.domain.mission.dto.MissionDto;
import com.modagbul.BE.domain.mission.service.MissionService;
import com.modagbul.BE.domain.user.dto.UserDto;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.modagbul.BE.domain.mission.constant.MissionConstant.MissionResponseMessage.*;

@RestController
@RequiredArgsConstructor
@Api(tags = "Mission CRUD API")
@RequestMapping("/api/v1/{teamId}/mission")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @ApiOperation(value = "미션 생성", notes = "미션을 생성합니다.")
    @PostMapping("/new")
    public ResponseEntity<ResponseDto<MissionDto.MissionRes>> createMission(@PathVariable Long teamId, @RequestBody MissionDto.MissionReq missionReq){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),CREATE_MISSION_SUCCESS.getMessage(),missionService.createMission(teamId,missionReq)));
    }

    @ApiOperation(value = "미션 수정", notes = "미션을 수정합니다.")
    @PostMapping("/{missionId}/update")
    public ResponseEntity<ResponseDto<MissionDto.MissionRes>> updateMission(@PathVariable Long teamId,@PathVariable Long missionId,@RequestBody MissionDto.MissionReq missionReq){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),UPDATE_MISSION_SUCCESS.getMessage(),missionService.updateMission(teamId,missionId,missionReq)));
    }
}
