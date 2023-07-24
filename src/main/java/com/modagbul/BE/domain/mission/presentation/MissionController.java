package com.modagbul.BE.domain.mission.presentation;

import com.modagbul.BE.domain.mission.application.dto.MissionBoardDto;
import com.modagbul.BE.domain.mission.application.dto.MissionDto;
import com.modagbul.BE.domain.mission.application.dto.MissionListDto;
import com.modagbul.BE.domain.mission.application.dto.MissionRateDto;
import com.modagbul.BE.domain.mission.application.service.MissionCreateService;
import com.modagbul.BE.domain.mission.application.service.MissionUpdateService;
import com.modagbul.BE.domain.mission.application.service.MissionBoardService;
import com.modagbul.BE.domain.mission.application.service.MissionService;
import com.modagbul.BE.domain.usermission.application.constant.Status;
import com.modagbul.BE.domain.usermission.application.dto.UserMissionDetailDto;
import com.modagbul.BE.domain.usermission.application.dto.UserMissionStatusDto;
import com.modagbul.BE.domain.usermission.application.service.UserMissionService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.modagbul.BE.domain.mission.application.constant.MissionConstant.MissionResponseMessage.*;

@RestController
@RequiredArgsConstructor
@Api(tags = "Mission CRUD API")
@RequestMapping("/api/v1/{teamId}/missions")
public class MissionController {

    private final UserMissionService userMissionService;
    private final MissionBoardService missionBoardService;

    private final MissionCreateService missionCreateService;
    private final MissionUpdateService missionUpdateService;
    private final MissionService missionService;

    @ApiOperation(value = "미션 생성", notes = "미션을 생성합니다.")
    @PostMapping("")
    public ResponseEntity<ResponseDto<MissionDto.MissionRes>> createMission(@PathVariable Long teamId, @RequestBody MissionDto.MissionReq missionReq){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),CREATE_MISSION_SUCCESS.getMessage(), missionCreateService.createMission(teamId,missionReq)));
    }

    @ApiOperation(value = "미션 수정", notes = "미션을 수정합니다.")
    @PutMapping("/{missionId}")
    public ResponseEntity<ResponseDto<MissionDto.MissionRes>> updateMission(@PathVariable Long teamId,@PathVariable Long missionId,@RequestBody MissionDto.MissionReq missionReq){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),UPDATE_MISSION_SUCCESS.getMessage(),missionUpdateService.updateMission(teamId,missionId,missionReq)));
    }

    @ApiOperation(value = "개인별 미션 리스트 조회", notes = "개인별 미션 리스트를 조회합니다.")
    @GetMapping("")
    public ResponseEntity<ResponseDto<List<MissionListDto>>> getMissions (@PathVariable Long teamId){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),GET_MISSION_LIST_SUCCESS.getMessage(),missionService.getMissionList(teamId)));
    }

    @ApiOperation(value = "개인별 미션 상세 페이지 조회", notes = "개인별 미션 상세 페이지를 조회합니다.")
    @GetMapping("/{missionId}")
    public ResponseEntity<ResponseDto<UserMissionDetailDto>> getMission (@PathVariable Long teamId, @PathVariable Long missionId){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),GET_MISSION_DETAIL_SUCCESS.getMessage(),missionService.getMissionDetail(teamId,missionId)));
    }
    @ApiOperation(value = "개인별 미션 제출", notes = "개인별 미션을 제출합니다.")
    @PostMapping("/{missionId}/submit")
    public ResponseEntity<ResponseDto<Status>> submitMission(@PathVariable Long teamId, @PathVariable Long missionId, @RequestBody String submitUrl){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),SUBMIT_MISSION_SUCCESS.getMessage(),userMissionService.submitUserMission(teamId,missionId,submitUrl)));
    }

    @ApiOperation(value = "개인별 미션 건너뛰기", notes = "개인별 미션을 건너뛰기합니다.")
    @PostMapping("/{missionId}/skip")
    public ResponseEntity<ResponseDto<Status>> skipMission(@PathVariable Long teamId, @PathVariable Long missionId, @RequestBody String skipReason){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),SKIP_MISSION_SUCCESS.getMessage(),userMissionService.skipUserMission(teamId,missionId,skipReason)));
    }

    @ApiOperation(value = "개인별 미션 인증 현황", notes = "개인별 미션 인증 현황을 조회합니다..")
    @GetMapping("/{missionId}/status")
    public ResponseEntity<ResponseDto<UserMissionStatusDto>> statusMission(@PathVariable Long teamId, @PathVariable Long missionId){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),STATUS_MISSION_SUCCESS.getMessage(),userMissionService.getUserMissionList(teamId,missionId)));
    }



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
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),STATUS_MISSION_SUCCESS.getMessage(),missionService.isLeader(teamId)));
    }




}
