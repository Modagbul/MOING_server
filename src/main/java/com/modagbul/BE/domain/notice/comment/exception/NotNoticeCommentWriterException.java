package com.modagbul.BE.domain.notice.comment.exception;

import com.modagbul.BE.domain.notice.comment.constant.NoticeCommentConstant;

public class NotNoticeCommentWriterException extends NoticeCommentException{
    public NotNoticeCommentWriterException(){
        super(NoticeCommentConstant.NoticeCommentExceptionList.NOT_NOTICE_COMMENT_WRITER_ERROR.getErrorCode(),
                NoticeCommentConstant.NoticeCommentExceptionList.NOT_NOTICE_COMMENT_WRITER_ERROR.getHttpStatus(),
                NoticeCommentConstant.NoticeCommentExceptionList.NOT_NOTICE_COMMENT_WRITER_ERROR.getMessage());
    }
}
