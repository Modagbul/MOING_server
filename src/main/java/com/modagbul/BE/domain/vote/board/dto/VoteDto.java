package com.modagbul.BE.domain.vote.board.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class VoteDto {

    @Getter
    @AllArgsConstructor
    @Builder
    @ApiModel(description = "투표 생성을 위한 요청 객체")
    @NoArgsConstructor
    public static class CreateVoteRequest {
        @NotBlank(message = "투표 제목을 입력해 주세요.")
        @ApiModelProperty(notes = "투표 제목을 입력해 주세요.")
        private String title;

        @NotBlank(message = "투표 내용을 입력해 주세요.")
        @ApiModelProperty(notes = "투표 내용을 입력해 주세요.")
        private String memo;

        @NotNull(message = "복수투표 여부를 입력해 주세요.")
        @ApiModelProperty(notes = "복수투표 여부를 입력해 주세요.")
        //복수투표 여부
        private boolean isMultiple;

        @NotNull(message = "익명투표 여부를 입력해 주세요.")
        @ApiModelProperty(notes = "익명투표 여부를 입력해 주세요.")
        //익명투표 여부
        private boolean isAnonymous;

        //투표 선택지
        private List<String> choices=new ArrayList<>();

    }

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
    @ApiModel(description = "투표하기를 위한 요청 객체")
    @NoArgsConstructor
    public static class DoVoteRequest {

        //선택한 투표 -> 복수투표, 일반투표 모두 가능
        private List<String> choices=new ArrayList<>();

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

        //투표글을 쓴 사람에 대한 정보
        private Long userId;
        private String nickName;
        private String userImageUrl;

        //안읽은 사람
        private List<String> notReadUsersNickName = new ArrayList<>();

        //각 선택지마다의 결과
        private List<VoteChoice> voteChoices=new ArrayList<>();
    }

    public class VoteChoice{

        //선택지 내용
        private String content;

        //그 선택지를 선택한 사람 수 (득표수)

        private Integer num;
        //그 선택지를 투표한 사람
        private List<String> voteUserNickName = new ArrayList<>();


    }

}
