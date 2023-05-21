package com.modagbul.BE.domain.mission.service;

import com.modagbul.BE.domain.mission.Exception.InvalidCompleteRateException;
import com.modagbul.BE.domain.mission.repository.MissionRepository;
import com.modagbul.BE.domain.team.repository.TeamRepository;
import com.modagbul.BE.domain.team_member.repository.TeamMemberRepository;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.usermission.repository.UserMissionRepository;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Long getTeamPercentForGraph(Long teamId) {

        //AtomicReference는 Java에서 멀티스레딩 환경에서 객체를 안전하게 공유하고 변경할 수 있도록 지원하는 클래스입니다.
        AtomicReference<Long> sum = new AtomicReference<>(0L);
        Long loginId = SecurityUtils.getLoggedInUser().getUserId();

        List<User> users = teamMemberRepository.findUserListByTeamId(teamId).orElseThrow(InvalidCompleteRateException::new);
        for (User user : users) {
            sum.updateAndGet(v -> v + userMissionRepository.getPersonalRateForGraphById(user.getUserId(), teamId).orElse(0L));
        }

        return sum.get()/users.size();
    }
}
