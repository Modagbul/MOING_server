package com.modagbul.BE.domain.mission.main.presentation;

import com.modagbul.BE.domain.mission.main.application.dto.MissionReq;
import com.modagbul.BE.domain.mission.main.application.dto.MissionRes;
import com.modagbul.BE.domain.mission.main.application.service.MissionCreateService;
import com.modagbul.BE.domain.mission.main.application.service.MissionUpdateService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.modagbul.BE.domain.mission.main.application.constant.MissionConstant.MissionResponseMessage.CREATE_MISSION_SUCCESS;
import static com.modagbul.BE.domain.mission.main.application.constant.MissionConstant.MissionResponseMessage.UPDATE_MISSION_SUCCESS;

@RestController
@RequiredArgsConstructor
@Api(tags = "Mission CRUD API")
@RequestMapping("/api/v1/{teamId}/missions")
public class MissionMainController {

    private final MissionCreateService missionCreateService;
    private final MissionUpdateService missionUpdateService;

    @ApiOperation(value = "미션 생성", notes = "미션을 생성합니다.")
    @PostMapping("")
    public ResponseEntity<ResponseDto<MissionRes>> createMission(@PathVariable Long teamId, @RequestBody MissionReq missionReq){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),CREATE_MISSION_SUCCESS.getMessage(), missionCreateService.createMission(teamId,missionReq)));
    }

    @ApiOperation(value = "미션 수정", notes = "미션을 수정합니다.")
    @PutMapping("/{missionId}")
    public ResponseEntity<ResponseDto<MissionRes>> updateMission(@PathVariable Long teamId,@PathVariable Long missionId,@RequestBody MissionReq missionReq){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),UPDATE_MISSION_SUCCESS.getMessage(),missionUpdateService.updateMission(teamId,missionId,missionReq)));
    }



}
