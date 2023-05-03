package com.modagbul.BE.domain.notice_comment.dto;

import com.modagbul.BE.domain.notice_comment.entity.NoticeComment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public abstract class NoticeCommentDto {
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

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "공지 댓글 생성을 위한 응답 객체")
    @NoArgsConstructor
    public static class CreateNoticeCommentResponse {
        private Long noticeCommentId;
    }


    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "공지 댓글 조회를 위한 응답 객체")
    @NoArgsConstructor
    public static class GetNoticeCommentResponse {

        private Long noticeCommentId;
        private String content;
        private Long userId;
        private String nickName;
        private String userImageUrl;
        private LocalDateTime createdDate;
    }


}
