package com.modagbul.BE.domain.usermission.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class UserMissionStatusDto {

    private String title;
    private String dueTo; // 남은 기간
    private Long statusNum;
    private List<UserMissionListDto> CompleteList;
    private List<UserMissionListDto> IncompleteList;
    private List<UserMissionListDto> PendingList;

}
