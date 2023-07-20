package com.modagbul.BE.domain.mission.exception;

import static com.modagbul.BE.domain.mission.application.constant.MissionConstant.MissionExceptionList.MISSION_AUTH_DENIED;

public class MissionAuthDeniedException extends MissionException {
    public MissionAuthDeniedException() {
        super(MISSION_AUTH_DENIED.getErrorCode(),
                MISSION_AUTH_DENIED.getHttpStatus(),
                MISSION_AUTH_DENIED.getMessage());
    }
}

