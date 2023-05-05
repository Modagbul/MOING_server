package com.modagbul.BE.domain.notice.board.exception;

import static com.modagbul.BE.domain.notice.board.constant.NoticeConstant.NoticeExceptionList.NOT_HAVE_NOTICE_BY_USER_ERROR;

public class NotFoundNoticeUserException extends NoticeException{
    public NotFoundNoticeUserException(){
        super(NOT_HAVE_NOTICE_BY_USER_ERROR.getErrorCode(),
                NOT_HAVE_NOTICE_BY_USER_ERROR.getHttpStatus(),
                NOT_HAVE_NOTICE_BY_USER_ERROR.getMessage());
    }
}
