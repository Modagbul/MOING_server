package com.modagbul.BE.domain.notice.comment.exception;

import static com.modagbul.BE.domain.notice.comment.constant.NoticeCommentConstant.NoticeCommentExceptionList.NOT_FOUND_NOTICE_COMMENT_ID_ERROR;

public class NotFoundNoticeCommentIdException extends NoticeCommentException {
    public NotFoundNoticeCommentIdException(){
        super(NOT_FOUND_NOTICE_COMMENT_ID_ERROR.getErrorCode(),
                NOT_FOUND_NOTICE_COMMENT_ID_ERROR.getHttpStatus(),
                NOT_FOUND_NOTICE_COMMENT_ID_ERROR.getMessage());
    }
}
