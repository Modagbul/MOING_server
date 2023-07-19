package com.modagbul.BE.domain.notice.board.application.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public abstract class NoticeBoardRequest {
    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "공지 생성을 위한 요청 객체")
    @NoArgsConstructor
    public static class CreateNoticeRequest {
        @NotBlank(message = "공지 제목을 입력해 주세요.")
        @ApiModelProperty(notes = "공지 제목을 입력해 주세요.")
        private String title;

        @NotBlank(message = "공지 내용을 입력해 주세요.")
        @ApiModelProperty(notes = "공지 내용을 입력해 주세요.")
        private String content;
    }
}
