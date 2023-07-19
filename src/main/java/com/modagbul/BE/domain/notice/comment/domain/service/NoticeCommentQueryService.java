package com.modagbul.BE.domain.notice.comment.domain.service;

import com.modagbul.BE.domain.notice.comment.domain.entity.NoticeComment;
import com.modagbul.BE.domain.notice.comment.domain.repsitory.NoticeCommentRepository;
import com.modagbul.BE.domain.notice.comment.exception.NotFoundNoticeCommentIdException;
import com.modagbul.BE.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

@DomainService
@Transactional
@RequiredArgsConstructor
public class NoticeCommentQueryService {
    private final NoticeCommentRepository noticeCommentRepository;

    public NoticeComment getNoticeCommentByCommentId(Long commentId) {
        return noticeCommentRepository.findNoticeCommentByCommentId(commentId).orElseThrow(() -> new NotFoundNoticeCommentIdException());
    }

    public List<NoticeComment> getNoticeCommentAllByNoticeId(Long noticeId) {
        return noticeCommentRepository.findNoticeCommentAllByNoticeId(noticeId);
    }
}
