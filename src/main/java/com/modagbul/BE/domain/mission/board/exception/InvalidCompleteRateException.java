package com.modagbul.BE.domain.mission.board.exception;

import com.modagbul.BE.domain.mission.main.application.constant.MissionConstant;
import com.modagbul.BE.domain.mission.main.exception.MissionException;

public class InvalidCompleteRateException extends MissionException {
    public InvalidCompleteRateException() {
        super(MissionConstant.MissionExceptionList.INVALID_COMPLETE_RATE.getErrorCode(),
                MissionConstant.MissionExceptionList.INVALID_COMPLETE_RATE.getHttpStatus(),
                MissionConstant.MissionExceptionList.INVALID_COMPLETE_RATE.getMessage());
    }
}
