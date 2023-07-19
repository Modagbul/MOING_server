package com.modagbul.BE.domain.vote.board.application.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public abstract class VoteRequest {

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
        private Boolean isMultiple;

        @NotNull(message = "익명투표 여부를 입력해 주세요.")
        @ApiModelProperty(notes = "익명투표 여부를 입력해 주세요.")
        private Boolean isAnonymous;

        //투표 선택지
        private List<String> choices=new ArrayList<>();

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
}
