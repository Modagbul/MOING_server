package com.modagbul.BE.domain.notice_comment.repsitory;

import com.modagbul.BE.domain.notice_comment.entity.NoticeComment;

import java.util.List;
import java.util.Optional;

public interface NoticeCommentRepositoryCustom {
    Optional<NoticeComment> findNotDeletedByCommentId(Long commentId);
    List<NoticeComment> findAllCommentsByNoticeId(Long noticeId);
}
