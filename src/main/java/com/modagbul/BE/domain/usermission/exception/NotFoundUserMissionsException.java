package com.modagbul.BE.domain.usermission.exception;


import static com.modagbul.BE.domain.usermission.application.constant.UserMissionExceptions.NOT_FOUND_USERMISSIONS;

public class NotFoundUserMissionsException extends UserMissionException {

    public NotFoundUserMissionsException() {
        super(NOT_FOUND_USERMISSIONS.getErrorCode(),
                NOT_FOUND_USERMISSIONS.getHttpStatus(),
                NOT_FOUND_USERMISSIONS.getMessage());
    }
}
