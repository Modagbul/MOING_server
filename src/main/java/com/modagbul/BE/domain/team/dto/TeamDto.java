package com.modagbul.BE.domain.team.dto;

import com.modagbul.BE.domain.team.constant.TeamConstant.Category;
import com.modagbul.BE.global.annotation.Enum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public abstract class TeamDto {

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "소모임 생성을 위한 요청 객체")
    @NoArgsConstructor
    public static class CreateTeamRequest {
        @NotBlank(message = "소모임 카테고리를 입력해 주세요.")
        @ApiModelProperty(notes = "소모임 카테고리를 입력해 주세요.")
        @Enum(enumClass = Category.class)
        private String category;

        @NotBlank(message = "소모임 이름을 입력해 주세요.")
        @ApiModelProperty(notes = "소모임 이름을 입력해 주세요.")
        private String name;

        @NotNull(message = "소모임 구성원수를 입력해 주세요.")
        @ApiModelProperty(notes = "소모임 구성원수를 입력해 주세요.")
        private Integer personnel;

        @NotBlank(message = "소모임 시작일을 입력해 주세요.")
        @ApiModelProperty(notes = "소모임 시작일을 입력해 주세요.")
        @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}"
                , message = "날짜 형식이 맞지 않습니다. yyyy-mm-ss 형식으로 입력해주세요.")
        private String startDate;

        @NotNull(message = "소모임 기간을 입력해 주세요.")
        @ApiModelProperty(notes = "소모임 기간을 입력해 주세요.")
        private Integer period;

        @NotBlank(message = "소모임 소개글을 입력해 주세요.")
        @ApiModelProperty(notes = "소모임 소개글을 입력해 주세요.")
        private String info;

        @NotBlank(message = "소모임 각오를 입력해 주세요.")
        @ApiModelProperty(notes = "소모임 각오를 입력해 주세요.")
        private String promise;

        @NotBlank(message = "소모임 대표사진 url을 입력해 주세요.")
        @ApiModelProperty(notes = "소모임 대표사진 url을 입력해 주세요.")
        private String profileImg;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "소모임 생성을 위한 응답 객체")
    public static class CreateTeamResponse {

        private Long teamId;
        private String invitationCode;

    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel(description = "소모임 참여코드 인증을 위한 요청 객체")
    public static class JoinTeamRequest {
        private String invitationCode;

    }

}
