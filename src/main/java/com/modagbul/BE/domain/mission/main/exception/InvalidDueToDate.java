package com.modagbul.BE.domain.mission.main.exception;

import com.modagbul.BE.domain.mission.main.application.constant.MissionConstant.MissionExceptionList;

public class InvalidDueToDate extends MissionException {
    public InvalidDueToDate() {
        super(MissionExceptionList.INVALID_DUE_TO_DATE.getErrorCode(),
                MissionExceptionList.INVALID_DUE_TO_DATE.getHttpStatus(),
                MissionExceptionList.INVALID_DUE_TO_DATE.getMessage());
    }
}
