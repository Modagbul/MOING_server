package com.modagbul.BE.domain.notice.comment.application.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

public  abstract class  NoticeCommentRequest {
    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "공지 댓글 생성을 위한 요청 객체")
    @NoArgsConstructor
    public static class CreateNoticeCommentRequest {
        @NotBlank(message = "댓글 내용을 입력해 주세요.")
        @ApiModelProperty(notes = "댓글 내용을 입력해 주세요.")
        private String content;
    }
}
