package com.modagbul.BE.domain.notice.exception;

import static com.modagbul.BE.domain.notice.constant.NoticeConstant.NoticeExceptionList.NOT_HAVE_NOTICEID_ERROR;

public class NotFoundNoticeIdException extends NoticeException{
    public NotFoundNoticeIdException(){
        super(NOT_HAVE_NOTICEID_ERROR.getErrorCode(),
                NOT_HAVE_NOTICEID_ERROR.getHttpStatus(),
                NOT_HAVE_NOTICEID_ERROR.getMessage());
    }
}
