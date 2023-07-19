package com.modagbul.BE.domain.notice.comment.application.service;

import com.modagbul.BE.domain.notice.comment.domain.entity.NoticeComment;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeCommentDeleteService {

    private final NoticeCommentValidateService noticeCommentValidateService;

    public void deleteNoticeComment(Long teamId, Long noticeId, Long noticeCommentId) {
        NoticeComment noticeComment=noticeCommentValidateService.validateNoticeComment(teamId, noticeId, noticeCommentId);
        noticeCommentValidateService.validateWriter(SecurityUtils.getLoggedInUser(),noticeComment);
        noticeComment.deleteNoticeComment();
    }
}
