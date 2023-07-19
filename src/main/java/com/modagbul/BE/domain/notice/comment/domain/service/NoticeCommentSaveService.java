package com.modagbul.BE.domain.notice.comment.domain.service;

import com.modagbul.BE.domain.notice.board.domain.entity.Notice;
import com.modagbul.BE.domain.notice.comment.domain.entity.NoticeComment;
import com.modagbul.BE.domain.notice.comment.domain.repsitory.NoticeCommentRepository;
import com.modagbul.BE.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@DomainService
@Transactional
@RequiredArgsConstructor
public class NoticeCommentSaveService {
    private final NoticeCommentRepository noticeCommentRepository;

    public void saveNoticeComment(NoticeComment noticeComment) {
        noticeCommentRepository.save(noticeComment);
    }
}
