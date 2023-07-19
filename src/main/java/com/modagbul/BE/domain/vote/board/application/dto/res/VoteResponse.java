package com.modagbul.BE.domain.vote.board.application.dto.res;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class VoteResponse {
    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "투표 생성을 위한 응답 객체")
    @NoArgsConstructor
    public static class CreateVoteResponse {
        private Long voteId;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "투표조회를 위한 응답 객체")
    @NoArgsConstructor
    public static class GetVoteDetailsResponse {

        private String title;
        private String memo;
        private LocalDateTime createdDate;
        private Boolean isMultiple;
        private Boolean isAnonymous;

        //투표글을 쓴 사람에 대한 정보
        private Long userId;
        private String nickName;
        private String userImageUrl;

        @QueryProjection
        public GetVoteDetailsResponse(String title, String memo, LocalDateTime createdDate,
                                      Long userId, String nickName, String userImageUrl,
                                      boolean isMultiple, boolean isAnonymous) {
            this.title = title;
            this.memo = memo;
            this.createdDate = createdDate;
            this.userId = userId;
            this.nickName = nickName;
            this.userImageUrl = userImageUrl;
            this.isMultiple = isMultiple;
            this.isAnonymous = isAnonymous;
        }

        //안읽은 사람
        private List<String> notReadUsersNickName = new ArrayList<>();

        //각 선택지마다의 결과
        private List<VoteChoice> voteChoices = new ArrayList<>();

        public void setNotReadUsersNickName(List<String> usersNickName) {
            this.notReadUsersNickName = usersNickName;
        }

        public void setVoteChoices(List<VoteChoice> voteChoices) {
            this.voteChoices = voteChoices;
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VoteChoice {

        //선택지 내용
        private String content;

        //그 선택지를 선택한 사람 수 (득표수)
        private Integer num;

        //그 선택지를 투표한 사람
        private List<String> voteUserNickName = new ArrayList<>();

    }

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "투표 전체 조회를 위한 응답 객체")
    @NoArgsConstructor
    public static class GetVoteAllResponse {
        private Long notReadNum;
        private List<VoteBlock> voteBlocks = new ArrayList<>();
    }

    @Getter
    @AllArgsConstructor
    @Builder
    @NoArgsConstructor
    public static class VoteBlock {
        private Long voteId;
        private String title;
        private String memo;
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
    @ApiModel(description = "안 읽은 투표 조회를 위한 응답 객체")
    @NoArgsConstructor
    public static class GetUnReadVoteResponse {
        private Long voteId;
        private String title;
        private String content;
    }

}
