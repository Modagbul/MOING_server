package com.modagbul.BE.domain.mission.service;

import com.modagbul.BE.domain.mission.Exception.InvalidCompleteRateException;
import com.modagbul.BE.domain.mission.dto.MissionBoardDto;
import com.modagbul.BE.domain.mission.repository.MissionRepository;
import com.modagbul.BE.domain.team.repository.TeamRepository;
import com.modagbul.BE.domain.team_member.repository.TeamMemberRepository;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.usermission.repository.UserMissionRepository;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class MissionBoardService {
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final TeamRepository teamRepository;
    private final TeamMemberRepository teamMemberRepository;

    // 소모임 그래프 - 개인별 : 개인 달성 미션수 / 전체 미션 수

    public Long getPersonalRateForGraph(Long teamId) {
        Long loginId = SecurityUtils.getLoggedInUser().getUserId();
//        return userMissionRepository.getPersonalRateForGraphById(loginId, teamId).orElseThrow(InvalidCompleteRateException::new);
        return userMissionRepository.getPersonalRateForGraphById(loginId, teamId).orElse(0L);
    }
    // 소모임 그래프 - 팀별 : 개인 미션 달성률의 합 / 소모임 인원 수
    public MissionBoardDto getTeamPercentForGraph(Long teamId) {

        //AtomicReference는 Java에서 멀티스레딩 환경에서 객체를 안전하게 공유하고 변경할 수 있도록 지원하는 클래스입니다.
        AtomicReference<Long> sum = new AtomicReference<>(0L);
        Long loginId = SecurityUtils.getLoggedInUser().getUserId();

        if(missionRepository.findMissionsByTeamId(teamId).isEmpty()){
            return new MissionBoardDto(0L, "불꽃이 생겨나고 있어요!");
        }

        List<User> users = teamMemberRepository.findUserListByTeamId(teamId).orElseThrow(InvalidCompleteRateException::new);
        for (User user : users) {
            sum.updateAndGet(v -> v + userMissionRepository.getPersonalRateForGraphById(user.getUserId(), teamId).orElse(0L));
        }

        return new MissionBoardDto((sum.get() / users.size()), fireMessageByTeamPercent(sum.get() / users.size()));
    }

    public String fireMessageByTeamPercent(Long teamPercent) {

        List<Map<Integer, String>> fireMessage = new ArrayList<>();

        Map<Integer,String> message1 = new HashMap<>();
        message1.put(0, "우리 모임의 불씨가 꺼져가고 있어요 ..... ");
        message1.put(1, "또 나만 진심인가불 ..... ");
        message1.put(2, "계세요 ..? 거기 누구 없어요 ...? ");
        message1.put(3, "중요한건 꺾여도 다시 하는 마음이불 ..🔥 ");
        message1.put(4, "후 불면 꺼질까 조마조마해불 🕯️ ");
        fireMessage.add(message1);

        Map<Integer,String> message2 = new HashMap<>();
        message2.put(0, "이제 열정에 부채질 할 시간 ");
        message2.put(1, "열정 불크업중!");
        message2.put(2, "더 부어 열정 on fire ! 🔥 ");
        message2.put(3, "이 모임 좀 핫하잖아? 🔥 ");
        fireMessage.add(message2);

        Map<Integer,String> message3 = new HashMap<>();
        message3.put(0, "HOT 모임! 아 핫핫모임! ");
        message3.put(1, "아앗 너무 뜨거울지도!");
        message3.put(2, "불크업한 제 불꽃 어때요? (포즈잡는중)");
        message3.put(3, "기름을 들이부어주라불!🔥");
        fireMessage.add(message3);

        Map<Integer,String> message4 = new HashMap<>();
        message4.put(0, "우리 모임의 불꽃이 활활 타오르는중 ");
        message4.put(1, "앗뜨앗뜨! 제 인생 가장 완벽한 불꽃이에요!");
        message4.put(2, "화르르 타오르는 도전의 불꽃");
        fireMessage.add(message4);


        if (0L<= teamPercent && teamPercent <= 19L) {
            return fireMessage.get(0).get((int)(Math.random()*5));
        }
        else if (20L<= teamPercent && teamPercent <= 49L) {
            return fireMessage.get(1).get((int)(Math.random()*4));
        }
        else if (50L<= teamPercent && teamPercent<= 79L) {
            return fireMessage.get(2).get((int)(Math.random()*4));
        }
        else {
            return fireMessage.get(3).get((int)(Math.random()*3));
        }

    }

}
