package com.modagbul.BE.domain.usermission.application.dto;

import com.modagbul.BE.domain.usermission.application.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserMissionDetailDto {
    private String title;
    private String dueTo;
    private String content;
    private String rule;
    private Status status;
    private String achieve;
    @Builder
    public UserMissionDetailDto(String title, String dueTo, String content, String rule, Status status, String achieve) {
        this.title = title;
        this.dueTo = dueTo;
        this.content = content;
        this.rule = rule;
        this.status = status;
        this.achieve = achieve;
    }

    public void setDueTo(String dueTo) {
        this.dueTo = dueTo;
    }
}
