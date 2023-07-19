package com.modagbul.BE.domain.vote.comment.application.dto.res;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public abstract class VoteCommentResponse {
    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "투표 댓글 생성을 위한 응답 객체")
    @NoArgsConstructor
    public static class CreateVoteCommentResponse {
        private Long voteCommentId;
    }


    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "투표 댓글 조회를 위한 응답 객체")
    @NoArgsConstructor
    public static class GetVoteCommentResponse {

        private Long voteCommentId;
        private String content;
        private Long userId;
        private String nickName;
        private String userImageUrl;
        private LocalDateTime createdDate;
    }

}
