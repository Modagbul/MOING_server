package com.modagbul.BE.domain.usermission.application.dto;

import com.modagbul.BE.domain.usermission.application.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserMissionIncompleteListDto {
    private Long userMissionId;

    private String nickname;
    private String profileImg;

    private Status status;
    private String archive;
    private LocalDateTime submitDate;

    private String fireStatus;



}

