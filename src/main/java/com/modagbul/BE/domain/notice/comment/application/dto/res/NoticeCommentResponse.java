package com.modagbul.BE.domain.notice.comment.application.dto.res;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public abstract class NoticeCommentResponse {
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
