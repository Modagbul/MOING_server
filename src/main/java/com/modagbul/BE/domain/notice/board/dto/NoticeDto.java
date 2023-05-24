package com.modagbul.BE.domain.notice.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "공지 조회를 위한 응답 객체")
    @NoArgsConstructor
    public static class GetNoticeDetailsResponse {

        private String title;
        private String content;
        private LocalDateTime createdDate;
        private Long userId;
        private String nickName;
        private String userImageUrl;
        private List<String> notReadUsersNickName = new ArrayList<>();

    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "공지 전체 조회를 위한 응답 객체")
    @NoArgsConstructor
    public static class GetNoticeAllResponse {
        private Long notReadNum;
        private List<NoticeBlock> noticeBlocks=new ArrayList<>();
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class NoticeBlock{
        private Long noticeId;
        private String title;
        private String content;
        private Long userId;
        private String nickName;
        private String userImageUrl;
        private Integer commentNum;
        private boolean isRead;

        private LocalDateTime createdDate;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "안 읽은 공지 조회를 위한 응답 객체")
    @NoArgsConstructor
    public static class GetUnReadNoticeResponse {
        private Long noticeId;
        private String title;
        private String content;
    }
}
