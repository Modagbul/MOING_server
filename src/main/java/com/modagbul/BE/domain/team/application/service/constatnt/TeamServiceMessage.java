package com.modagbul.BE.domain.team.application.service.constatnt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TeamServiceMessage {
    VALID_TEAMNAME("가능한 소모임 이름입니다"),
    EXISTED_TEAMNAME("이미 존재하는 소모임 이름입니다");
    private final String value;
}