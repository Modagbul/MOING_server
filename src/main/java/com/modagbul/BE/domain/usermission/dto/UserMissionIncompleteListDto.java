package com.modagbul.BE.domain.usermission.dto;

import com.modagbul.BE.domain.usermission.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

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

