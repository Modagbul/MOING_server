package com.modagbul.BE.domain.notice.dto;

import com.modagbul.BE.domain.team.constant.TeamConstant;
import com.modagbul.BE.global.annotation.Enum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public abstract class NoticeDto {

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "공지 생성을 위한 요청 객체")
    @NoArgsConstructor
    public static class CreateNoticeRequest {
        @NotBlank(message = "공지 제목을 입력해 주세요.")
        @ApiModelProperty(notes = "공지 제목을 입력해 주세요.")
        private String title;

        @NotNull(message = "소모임 id를 입력해 주세요.")
        @ApiModelProperty(notes = "소모임 id를 입력해 주세요.")
        private Long teamId;

        @NotBlank(message = "공지 내용을 입력해 주세요.")
        @ApiModelProperty(notes = "공지 내용을 입력해 주세요.")
        private String content;
    }
    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "공지 생성을 위한 응답 객체")
    @NoArgsConstructor
    public static class CreateNoticeResponse {
        private Long noticeId;
    }

}
