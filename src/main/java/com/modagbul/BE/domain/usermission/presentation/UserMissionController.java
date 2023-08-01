package com.modagbul.BE.domain.usermission.presentation;

import com.modagbul.BE.domain.mission.main.application.dto.MissionListDto;
import com.modagbul.BE.domain.mission.main.application.service.MissionReadService;
import com.modagbul.BE.domain.usermission.application.constant.Status;
import com.modagbul.BE.domain.usermission.application.dto.UserMissionDetailDto;
import com.modagbul.BE.domain.usermission.application.dto.UserMissionStatusDto;
import com.modagbul.BE.domain.usermission.application.service.UserMissionCreateService;
import com.modagbul.BE.domain.usermission.application.service.UserMissionReadService;
import com.modagbul.BE.domain.usermission.application.service.UserMissionService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.modagbul.BE.domain.mission.main.application.constant.MissionConstant.MissionResponseMessage.*;

@RestController
@RequiredArgsConstructor
@Api(tags = "Mission CRUD API")
@RequestMapping("/api/v1/{teamId}/missions")
public class UserMissionController {

    private final UserMissionCreateService userMissionCreateService;
    private final UserMissionReadService userMissionReadService;
    private final MissionReadService missionReadService;

    @ApiOperation(value = "개인별 미션 제출", notes = "개인별 미션을 제출합니다.")
    @PostMapping("/{missionId}/submit")
    public ResponseEntity<ResponseDto<Status>> submitMission(@PathVariable Long teamId, @PathVariable Long missionId, @RequestBody String submitUrl){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),SUBMIT_MISSION_SUCCESS.getMessage(),userMissionCreateService.submitUserMission(teamId,missionId,submitUrl)));
    }

    @ApiOperation(value = "개인별 미션 건너뛰기", notes = "개인별 미션을 건너뛰기합니다.")
    @PostMapping("/{missionId}/skip")
    public ResponseEntity<ResponseDto<Status>> skipMission(@PathVariable Long teamId, @PathVariable Long missionId, @RequestBody String skipReason){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),SKIP_MISSION_SUCCESS.getMessage(),userMissionCreateService.skipUserMission(teamId,missionId,skipReason)));
    }


    @ApiOperation(value = "개인별 미션 인증 현황", notes = "개인별 미션 인증 현황을 조회합니다..")
    @GetMapping("/{missionId}/status")
    public ResponseEntity<ResponseDto<UserMissionStatusDto>> statusMission(@PathVariable Long teamId, @PathVariable Long missionId){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),STATUS_MISSION_SUCCESS.getMessage(),userMissionReadService.getUserMissionList(teamId,missionId)));
    }


    @ApiOperation(value = "개인별 미션 리스트 조회", notes = "개인별 미션 리스트를 조회합니다.")
    @GetMapping("")
    public ResponseEntity<ResponseDto<List<MissionListDto>>> getMissions (@PathVariable Long teamId){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),GET_MISSION_LIST_SUCCESS.getMessage(), missionReadService.getMissionList(teamId)));
    }

    @ApiOperation(value = "개인별 미션 상세 페이지 조회", notes = "개인별 미션 상세 페이지를 조회합니다.")
    @GetMapping("/{missionId}")
    public ResponseEntity<ResponseDto<UserMissionDetailDto>> getMission (@PathVariable Long teamId, @PathVariable Long missionId){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),GET_MISSION_DETAIL_SUCCESS.getMessage(), missionReadService.getMissionDetail(teamId,missionId)));
    }

}
