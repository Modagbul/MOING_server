package com.modagbul.BE.domain.usermission.application.service;

import com.modagbul.BE.domain.fire.domain.service.FireQueryService;
import com.modagbul.BE.domain.mission.main.domain.service.MissionQueryService;
import com.modagbul.BE.domain.mission.main.domain.entity.Mission;
import com.modagbul.BE.domain.team.domain.repository.TeamRepository;

import com.modagbul.BE.domain.team.domain.service.TeamQueryService;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.domain.usermission.application.constant.Status;
import com.modagbul.BE.domain.usermission.application.dto.UserMissionListDto;
import com.modagbul.BE.domain.usermission.application.dto.UserMissionStatusDto;
import com.modagbul.BE.domain.usermission.domain.entity.UserMission;
import com.modagbul.BE.domain.usermission.domain.service.UserMissionQueryService;
import com.modagbul.BE.domain.usermission.domain.service.UserMissionSaveService;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserMissionService {

    private final UserRepository userRepository;

    private final UserMissionQueryService userMissionQueryService;
    private final UserMissionSaveService userMissionSaveService;
    private final MissionQueryService missionQueryService;
    private final FireQueryService fireQueryService;







}
