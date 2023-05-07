package com.modagbul.BE.domain.mission.dto;

import com.modagbul.BE.domain.usermission.constant.Status;
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
    private Status status;

    public MissionListDto(Long missionId, String title, String dueTo) {
        this.missionId = missionId;
        this.title = title;
        this.dueTo = dueTo;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDueTo(String dueTo) {
        this.dueTo = dueTo;
    }
}