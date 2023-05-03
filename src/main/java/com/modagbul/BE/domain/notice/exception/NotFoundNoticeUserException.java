package com.modagbul.BE.domain.notice.exception;

import com.modagbul.BE.domain.notice.constant.NoticeConstant;

import static com.modagbul.BE.domain.notice.constant.NoticeConstant.NoticeExceptionList.NOT_HAVE_NOTICE_BY_USER_ERROR;

public class NotFoundNoticeUserException extends NoticeException{
    public NotFoundNoticeUserException(){
        super(NOT_HAVE_NOTICE_BY_USER_ERROR.getErrorCode(),
                NOT_HAVE_NOTICE_BY_USER_ERROR.getHttpStatus(),
                NOT_HAVE_NOTICE_BY_USER_ERROR.getMessage());
    }
}
