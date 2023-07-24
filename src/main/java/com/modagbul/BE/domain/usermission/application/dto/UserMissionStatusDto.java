package com.modagbul.BE.domain.usermission.application.dto;

import com.modagbul.BE.domain.usermission.application.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserMissionStatusDto {

    private String title;
    private String remainDay; // 남은 기간
    private int completeUser;
    private int incompleteUser;

    private List<UserMissionListDto> CompleteList;
    private List<UserMissionListDto> IncompleteList;
    private List<Long> fireUserMissionList;

    private Status myStatus;

    public UserMissionStatusDto(String title, List<UserMissionListDto> completeList, List<UserMissionListDto> incompleteList,Status status) {
        this.title = title;
        this.CompleteList = completeList;
        this.IncompleteList = incompleteList;
        this.myStatus = status;
    }

    public void setUserNum(int completeUser, int incompleteUser) {
        this.completeUser = completeUser;
        this.incompleteUser = incompleteUser;

    }

    public void setRemainDay(String remainDay) {
        this.remainDay = remainDay;
    }

    public void setFireUserMissionList(List<Long> fireUserMissionList) {
        //set으로 중복 제거 후 다시 list로 변환
        Set<Long> set = Set.copyOf(fireUserMissionList);
        fireUserMissionList.clear();
        fireUserMissionList.addAll(set);
        this.fireUserMissionList = fireUserMissionList;
    }

    public void setMyStatus(Status myStatus) {
        this.myStatus = myStatus;
    }
}
