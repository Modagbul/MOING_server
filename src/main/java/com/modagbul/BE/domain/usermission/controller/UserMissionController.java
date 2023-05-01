package com.modagbul.BE.domain.usermission.controller;

import com.modagbul.BE.domain.usermission.dto.UserMissionDetailDto;
import com.modagbul.BE.domain.usermission.dto.UserMissionListDto;
import com.modagbul.BE.domain.usermission.service.UserMissionService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.modagbul.BE.domain.usermission.constant.UserMissionResponseMessage.GET_MISSION_DETAIL_SUCCESS;
import static com.modagbul.BE.domain.usermission.constant.UserMissionResponseMessage.GET_MISSION_LIST_SUCCESS;


@RestController
@RequiredArgsConstructor
@Api(tags = "Mission Submit API")
@RequestMapping("/api/v1/{teamId}/mission")
public class UserMissionController {

    private final UserMissionService userMissionService;
    @GetMapping("")
    public ResponseEntity<ResponseDto<List<UserMissionListDto>>> getMissions (@PathVariable Long teamId){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),GET_MISSION_LIST_SUCCESS.getMessage(),userMissionService.getUserMissionList(teamId)));
    }

    @GetMapping("/{missionId}")
    public ResponseEntity<ResponseDto<UserMissionDetailDto>> getMission (@PathVariable Long teamId, @PathVariable Long missionId){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(),GET_MISSION_DETAIL_SUCCESS.getMessage(),userMissionService.getUserMissionDetail(teamId,missionId)));
    }
}


