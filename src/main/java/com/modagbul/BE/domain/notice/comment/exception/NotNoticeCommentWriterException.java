package com.modagbul.BE.domain.notice.comment.exception;


import com.modagbul.BE.domain.notice.comment.exception.constant.NoticeCommentExceptionList;

public class NotNoticeCommentWriterException extends NoticeCommentException{
    public NotNoticeCommentWriterException(){
        super(NoticeCommentExceptionList.NOT_NOTICE_COMMENT_WRITER_ERROR.getErrorCode(),
                NoticeCommentExceptionList.NOT_NOTICE_COMMENT_WRITER_ERROR.getHttpStatus(),
                NoticeCommentExceptionList.NOT_NOTICE_COMMENT_WRITER_ERROR.getMessage());
    }
}
