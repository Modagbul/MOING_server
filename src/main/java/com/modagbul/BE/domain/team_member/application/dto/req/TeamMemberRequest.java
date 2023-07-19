package com.modagbul.BE.domain.team_member.application.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public abstract class TeamMemberRequest {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "소모임 참여코드 인증을 위한 요청 객체")
    public static class JoinTeamRequest {
        @NotBlank(message = "소모임 초대코드를 입력해 주세요.")
        @ApiModelProperty(notes = "소모임 초대코드를 입력해 주세요.")
        private String invitationCode;

    }
}
