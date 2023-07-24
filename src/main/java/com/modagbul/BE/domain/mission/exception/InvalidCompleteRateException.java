package com.modagbul.BE.domain.mission.exception;

import com.modagbul.BE.domain.mission.application.constant.MissionConstant;

public class InvalidCompleteRateException extends MissionException {
    public InvalidCompleteRateException() {
        super(MissionConstant.MissionExceptionList.INVALID_COMPLETE_RATE.getErrorCode(),
                MissionConstant.MissionExceptionList.INVALID_COMPLETE_RATE.getHttpStatus(),
                MissionConstant.MissionExceptionList.INVALID_COMPLETE_RATE.getMessage());
    }
}
