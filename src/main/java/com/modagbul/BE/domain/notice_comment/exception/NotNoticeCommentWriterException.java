package com.modagbul.BE.domain.notice_comment.exception;

import static com.modagbul.BE.domain.notice_comment.constant.NoticeCommentConstant.NoticeCommentExceptionList.NOT_NOTICE_COMMENT_WRITER_ERROR;

public class NotNoticeCommentWriterException extends NoticeCommentException{
    public NotNoticeCommentWriterException(){
        super(NOT_NOTICE_COMMENT_WRITER_ERROR.getErrorCode(),
                NOT_NOTICE_COMMENT_WRITER_ERROR.getHttpStatus(),
                NOT_NOTICE_COMMENT_WRITER_ERROR.getMessage());
    }
}
