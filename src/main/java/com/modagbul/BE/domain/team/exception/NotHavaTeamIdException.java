package com.modagbul.BE.domain.team.exception;

import static com.modagbul.BE.domain.team.constant.TeamConstant.TeamExceptionList.NOT_HAVE_TEAMID_ERROR;

public class NotHavaTeamIdException extends TeamException{
    public NotHavaTeamIdException(){
        super(NOT_HAVE_TEAMID_ERROR.getErrorCode(),
                NOT_HAVE_TEAMID_ERROR.getHttpStatus(),
                NOT_HAVE_TEAMID_ERROR.getMessage());
    }
}
