package com.modagbul.BE.domain.fire.application.service;

import com.modagbul.BE.domain.fire.domain.entity.Fire;
import com.modagbul.BE.domain.fire.domain.service.FireSaveService;
import com.modagbul.BE.domain.fire.exception.FireAuthDeniedException;
import com.modagbul.BE.domain.fire.domain.repository.FireRepository;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.domain.usermission.application.constant.Status;
import com.modagbul.BE.domain.usermission.domain.entity.UserMission;
import com.modagbul.BE.domain.usermission.domain.service.UserMissionQueryService;
import com.modagbul.BE.domain.usermission.exception.NotFoundUserMissionsException;
import com.modagbul.BE.domain.usermission.domain.repository.UserMissionRepository;
import com.modagbul.BE.fcm.service.FcmService;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.modagbul.BE.fcm.dto.FcmDto.*;

@Service
@RequiredArgsConstructor
public class FireService {

    private final UserRepository userRepository;
    private final FcmService fcmService;

    private final UserMissionQueryService userMissionQueryService;
    private final FireSaveService fireSaveService;

    public Long fire(Long userMissionId) {
        Long loginId = SecurityUtils.getLoggedInUser().getUserId();
        User loginUser = userRepository.findById(loginId).orElseThrow(() -> new IllegalStateException("해당 유저를 찾을 수 없습니다."));

        UserMission userMission = userMissionQueryService.getUserMissionById(userMissionId);

        if(userMission.getStatus().equals(Status.INCOMPLETE)){
            Fire fire = new Fire();
            fire.createFire(userMission,loginUser);
            fireSaveService.saveFire(fire);

            List<String> strings = fireMessage(loginUser.getNickName(), userMission.getUser().getNickName(), userMission.getMission().getTitle()).get((int) (Math.random() * 2));
            ToSingleRequest toSingleRequest = new ToSingleRequest(
                    userMission.getUser().getFcmToken(),strings.get(0),strings.get(1)
            );
            System.out.println(toSingleRequest.getTitle()+toSingleRequest.getBody());
            if(userMission.getUser().isFirePush()) {
                fcmService.sendSingleDevice(toSingleRequest);
            }

        }
        else{
            throw new FireAuthDeniedException();
        }


        return userMissionId;
    }


    public Map<Integer, List<String>> fireMessage(String sender, String receiver , String title) {

        Map<Integer, List<String>> fire = new HashMap<>();

        fire.put(0, List.of( "어라… 왜 이렇게 발등이 뜨겁지? 🤔  ", sender + "님이 " + receiver + "님에게 불을 던졌어요! 어서 미션을 인증해볼까요?" ));
        fire.put(1,List.of("⚠️불조심⚠️ ["+title+"] 미션", receiver+"님! " + sender+"님이 던진 불에 타버릴지도 몰라요! 어서 인증하러갈까요? "));
        return fire;
    }
}
