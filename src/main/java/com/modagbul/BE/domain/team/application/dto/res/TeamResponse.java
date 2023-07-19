package com.modagbul.BE.domain.team.application.dto.res;

import com.modagbul.BE.domain.team.domain.entity.Team;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;


public class TeamResponse {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "소모임 생성을 위한 응답 객체")
    public static class CreateTeamResponse {

        private Long teamId;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "소모임 초대코드 조회를 위한 응답 객체")
    public static class GetInviteCodeResponse {

        private String invitationCode;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "소모임 정보 조회를 위한 응답 객체")
    public static class GetTeamInfo {
        private String name;
        private String endDate;
        private String profileImg;
    }

    @Getter
    @ApiModel(description = "진행 중인 소모임 정보를 위한 응답 객체")
    public static class GetTeamResponse {
        private String userNickName;
        private Long inProgressNum;
        private List<TeamBlock> teamBlocks = new ArrayList<>();

        public GetTeamResponse(Long inProgressNum, List<TeamBlock> teamBlocks) {
            this.inProgressNum = inProgressNum;
            this.teamBlocks = teamBlocks;
        }

        public void setUserNickName(String userNickName) {
            this.userNickName = userNickName;
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class TeamBlock {
        private Long teamId;
        private String name;
        private Integer personnel;
        private LocalDate startDate;
        private LocalDate endDate;
        private String profileImg;
        private boolean approvalStatus;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "닉네임 중복 검사를 위한 응답 객체")
    public static class CheckTeamNameResponse {
        private String result;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "목표보드 프로필을 위한 응답 객체")
    public static class GetProfileResponse {
        private String name;
        private String profileImg;
        private String remainingPeriod;
        private String nowTime;

        public GetProfileResponse(Team team) {
            this.name = team.getName();
            this.profileImg = team.getProfileImg();
            this.remainingPeriod = getRemainingDays(team.getEndDate());
            this.nowTime = getNowTime();
        }

        public String getRemainingDays(LocalDate endDate) {
            LocalDate today = LocalDate.now(ZoneId.of("Asia/Seoul"));
            long remainingDays = ChronoUnit.DAYS.between(today, endDate);
            if (remainingDays == 0) {
                return "D-Day";
            } else {
                return "D-" + remainingDays;
            }
        }

        public String getNowTime() {
            LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
            return now.format(formatter);
        }
    }
}
