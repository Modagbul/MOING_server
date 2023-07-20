package com.modagbul.BE.domain.mission.application.dto;

import com.modagbul.BE.domain.usermission.application.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MissionListDto {
    private Long missionId;
    private String title;
    private String dueTo;
    private String dueDate;
    private Status status;

    public MissionListDto(Long missionId, String title, String dueTo,String dueDate) {
        this.missionId = missionId;
        this.title = title;
        this.dueTo = dueTo;
        this.dueDate = dueDate;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDueTo(String dueTo) {
        this.dueTo = dueTo;
    }
}