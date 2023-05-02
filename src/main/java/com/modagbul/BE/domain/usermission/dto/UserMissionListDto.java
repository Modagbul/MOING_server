package com.modagbul.BE.domain.usermission.dto;

import com.modagbul.BE.domain.usermission.constant.Status;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class UserMissionListDto {
    private Long missionId;
    private String title;
    private String dueTo;
    private Status status;
}
