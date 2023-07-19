package com.modagbul.BE.domain.notice.board.exception;


import static com.modagbul.BE.domain.notice.board.exception.constant.NoticeExceptionList.NOT_NOTICE_WRITER_ERROR;

public class NotNoticeWriterException extends NoticeException{
    public NotNoticeWriterException(){
        super(NOT_NOTICE_WRITER_ERROR.getErrorCode(),
                NOT_NOTICE_WRITER_ERROR.getHttpStatus(),
                NOT_NOTICE_WRITER_ERROR.getMessage());
    }
}
