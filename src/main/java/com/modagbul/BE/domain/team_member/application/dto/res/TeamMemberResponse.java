package com.modagbul.BE.domain.team_member.application.dto.res;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TeamMemberResponse {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "소모임 참여코드 인증을 위한 응답 객체")
    public static class JoinTeamResponse {
        private Long teamId;
        private String profileImg;

    }
}
