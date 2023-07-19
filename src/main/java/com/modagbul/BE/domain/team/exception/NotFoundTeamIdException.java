package com.modagbul.BE.domain.team.exception;


import static com.modagbul.BE.domain.team.exception.constant.TeamExceptionList.NOT_HAVE_TEAMID_ERROR;

public class NotFoundTeamIdException extends TeamException{
    public NotFoundTeamIdException(){
        super(NOT_HAVE_TEAMID_ERROR.getErrorCode(),
                NOT_HAVE_TEAMID_ERROR.getHttpStatus(),
                NOT_HAVE_TEAMID_ERROR.getMessage());
    }
}
