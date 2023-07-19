package com.modagbul.BE.domain.notice.comment.application.service;

import com.modagbul.BE.domain.notice.board.application.service.NoticeBoardValidateService;
import com.modagbul.BE.domain.notice.comment.domain.entity.NoticeComment;
import com.modagbul.BE.domain.notice.comment.domain.service.NoticeCommentQueryService;
import com.modagbul.BE.domain.notice.comment.exception.NotFoundNoticeCommentIdException;
import com.modagbul.BE.domain.notice.comment.exception.NotNoticeCommentWriterException;
import com.modagbul.BE.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeCommentValidateService {

    private final NoticeBoardValidateService noticeBoardValidateService;
    private final NoticeCommentQueryService noticeCommentQueryService;
    public NoticeComment validateNoticeComment(Long teamId, Long noticeId, Long noticeCommentId) {
        noticeBoardValidateService.validateNotice(teamId, noticeId);
        return noticeCommentQueryService.getNoticeCommentByCommentId(noticeCommentId);
    }

    public void validateWriter(User user, NoticeComment noticeComment){
        if(noticeComment.getUser().getUserId()!=user.getUserId())
            throw new NotNoticeCommentWriterException();
    }

}
