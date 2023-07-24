package com.modagbul.BE.domain.usermission.application.dto;

import com.modagbul.BE.domain.usermission.application.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserMissionDetailDto {
    private String title;
    private String dueTo;
    private String content;
    private String rule;
    private Status status;
    private String achieve;

    public void setDueTo(String dueTo) {
        this.dueTo = dueTo;
    }
}
