package com.modagbul.BE.domain.mission.board.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MissionBoardDto {
    private Long percent;
    private String fireCopy;

    public void setPercent(Long percent) {
        this.percent = percent;
    }

    public void setFireCopy(String fireCopy) {
        this.fireCopy = fireCopy;
    }
}
