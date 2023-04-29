package com.modagbul.BE.domain.team.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class TeamConstant {

    @Getter
    @RequiredArgsConstructor
    public enum ETeamResponseMessage{
        CREATE_TEAM_SUCCESS("소모임을 생성하였습니다");
        private final String message;
    }
    @Getter
    @RequiredArgsConstructor
    public enum Category{
        SPORTS("스포츠/운동"),
        HABIT("생활습관 개선"),
        TEST("시험/취업준비"),
        STUDY("스터디/공부"),
        READING("독서"),
        ETC("그외 자기계발");

        private final String value;
        public String getValue(){
            return value;
        }
    }
}
