package com.modagbul.BE.domain.mission.Exception;

import com.modagbul.BE.domain.mission.constant.MissionConstant;
import com.modagbul.BE.domain.mission.constant.MissionConstant.MissionExceptionList;

public class NotFoundMissionException extends MissionException{
    public NotFoundMissionException() {
        super(MissionExceptionList.NOT_FOUND_MISSION.getErrorCode(),
                MissionExceptionList.NOT_FOUND_MISSION.getHttpStatus(),
                MissionExceptionList.NOT_FOUND_MISSION.getMessage());
    }
}
