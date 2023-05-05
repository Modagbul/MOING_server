package com.modagbul.BE.domain.fire.controller;

import com.modagbul.BE.domain.fire.service.FireService;
import com.modagbul.BE.domain.mission.dto.MissionDto;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static com.modagbul.BE.domain.fire.constant.FireResponseMessage.THROW_FIRE_SUCCESS;
import static com.modagbul.BE.domain.mission.constant.MissionConstant.MissionResponseMessage.CREATE_MISSION_SUCCESS;

@RestController
@RequiredArgsConstructor
@Api(tags = "Mission CRUD API")
@RequestMapping("/api/v1/{teamId}/missions")
public class FireController {

    private final FireService fireService;

    @ApiOperation(value = "불 던지기", notes = "해당 미션에 불을 던집니다.")
    @PostMapping("{missionId}/{userMissionId}/fire")
    public ResponseEntity<ResponseDto<Long>> throwFire(@PathVariable Long missionId , @PathVariable Long teamId, @PathVariable Long userMissionId){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),THROW_FIRE_SUCCESS.getMessage(),fireService.fire(userMissionId)));
    }

}
