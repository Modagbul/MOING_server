package com.modagbul.BE.domain.mission.dto;

import com.modagbul.BE.domain.usermission.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MissionDetailDto {
    private String title;
    private String dueTo;
    private String content;
    private String rule;
    private Status status;

    public MissionDetailDto(String title, String dueTo, String content, String rule) {
        this.title = title;
        this.dueTo = dueTo;
        this.content = content;
        this.rule = rule;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDueTo(String dueTo) {
        this.dueTo = dueTo;
    }
}
