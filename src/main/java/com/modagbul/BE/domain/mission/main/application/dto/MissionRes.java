package com.modagbul.BE.domain.mission.main.application.dto;

import com.modagbul.BE.domain.usermission.application.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MissionRes {
    private String title;
    private String dueTo;
    private String content;
    private String rule;
    private Status status;
}