package com.modagbul.BE.domain.vote.board.exception.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum VoteExceptionList {
    NOT_HAVE_VOTE_ID_ERROR("V0001", HttpStatus.NOT_FOUND, "해당 voteId인 투표가 존재하지 않습니다."),
    NOT_HAVE_VOTE_BY_USER_ERROR("V0002", HttpStatus.NOT_FOUND, "해당 유저의 투표가 존재하지 않습니다."),
    NOT_VOTE_WRITER_ERROR("V0003", HttpStatus.NOT_FOUND, "해당 투표를 작성한 유저가 아닙니다."),
    NOT_HAVE_VOTE_CONTENT_ERROR("V0004", HttpStatus.NOT_FOUND, "해당 choice를 가진 투표 선택지가 없습니다. choice 리스트를 다시 확인하세요.");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;
}
