package com.modagbul.BE.domain.mission.main.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public  class MissionReq {
    private String title;
    private String dueTo;
    private String content;
    private String rule;
}