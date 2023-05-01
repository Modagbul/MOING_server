package com.modagbul.BE.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MissionDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionReq {
        private String title;
        private String dueTo;
        private String content;
        private String rule;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionRes {
        private String title;
        private String dueTo;
        private String content;
        private String rule;
    }
}
