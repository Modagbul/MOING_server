package com.modagbul.BE.domain.mission.constant;

import com.modagbul.BE.domain.mission.entity.Mission;
import com.modagbul.BE.domain.user.entity.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class MissionBoardFireMessage {

    private final Mission mission;
    private final List<User> notCompleteMembers = new ArrayList<>();
    private String title;


    public MissionBoardFireMessage(Mission mission) {
        this.mission = mission;
    }

    public void init(List<User> teamMembers) {

        for (User user : teamMembers) {
            if(user.isRemindPush())
                // 완료하지 않은 멤버만
                this.notCompleteMembers.add(user);
        }
    }






    public Map<Integer,List<String>> messageInitDDay(String nickname,String title) {
        Map<Integer,List<String>> dDay = new HashMap<>();
        dDay.put(0, List.of(nickname + "님, 오늘은 미션 종료일이에요!", "미션 인증을 까먹으신 건 아니겠죠? 🥹"));
        dDay.put(1, List.of(nickname + "님, 오늘까지 [" + this.title + "] 미션을 완료해주세요!", "모임원들이 애타게 기다리고 있어요😭"));
        dDay.put(2, List.of(nickname + "님, 아직 [" + this.title + "] 미션을 인증하지 않은 것 같아요", "모임의 불꽃이 곧 사그라들어요…🥹"));
        dDay.put(3, List.of("[오늘마감] " + this.title, "모임 친구들이 " + nickname + "님의 인증을 기다리고 있어요!"));
        return dDay;
    }

}
