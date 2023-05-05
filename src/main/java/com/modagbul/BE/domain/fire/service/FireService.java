package com.modagbul.BE.domain.fire.service;

import com.modagbul.BE.domain.fire.entity.Fire;
import com.modagbul.BE.domain.fire.repository.FireRepository;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.domain.usermission.entity.UserMission;
import com.modagbul.BE.domain.usermission.exception.NotFoundUserMissionsException;
import com.modagbul.BE.domain.usermission.repository.UserMissionRepository;
import com.modagbul.BE.domain.usermission.service.UserMissionService;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FireService {

    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;
    private final FireRepository fireRepository;

    public Long fire(Long userMissionId) {
        Long loginId = SecurityUtils.getLoggedInUser().getUserId();
        User loginUser = userRepository.findById(loginId).orElseThrow(() -> new IllegalStateException("해당 유저를 찾을 수 없습니다."));

        UserMission userMission = userMissionRepository.findById(userMissionId).orElseThrow(NotFoundUserMissionsException::new);

        Fire fire = new Fire();
        fire.createFire(userMission,loginUser);
        fireRepository.save(fire);

        return userMissionId;
    }
}
