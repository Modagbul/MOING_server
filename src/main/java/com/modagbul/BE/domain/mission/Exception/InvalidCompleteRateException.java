package com.modagbul.BE.domain.mission.Exception;

import com.modagbul.BE.domain.mission.constant.MissionConstant;

public class InvalidCompleteRateException extends MissionException {
    public InvalidCompleteRateException() {
        super(MissionConstant.MissionExceptionList.INVALID_COMPLETE_RATE.getErrorCode(),
                MissionConstant.MissionExceptionList.INVALID_COMPLETE_RATE.getHttpStatus(),
                MissionConstant.MissionExceptionList.INVALID_COMPLETE_RATE.getMessage());
    }
}
