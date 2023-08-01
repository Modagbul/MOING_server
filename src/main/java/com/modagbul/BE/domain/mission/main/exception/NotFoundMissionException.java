package com.modagbul.BE.domain.mission.main.exception;

import com.modagbul.BE.domain.mission.main.application.constant.MissionConstant.MissionExceptionList;

public class NotFoundMissionException extends MissionException {
    public NotFoundMissionException() {
        super(MissionExceptionList.NOT_FOUND_MISSION.getErrorCode(),
                MissionExceptionList.NOT_FOUND_MISSION.getHttpStatus(),
                MissionExceptionList.NOT_FOUND_MISSION.getMessage());
    }
}
