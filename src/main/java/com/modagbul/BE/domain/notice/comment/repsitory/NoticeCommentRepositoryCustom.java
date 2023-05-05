package com.modagbul.BE.domain.notice.comment.repsitory;

import com.modagbul.BE.domain.notice.comment.entity.NoticeComment;

import java.util.List;
import java.util.Optional;

public interface NoticeCommentRepositoryCustom {
    Optional<NoticeComment> findNotDeletedByCommentId(Long commentId);
    List<NoticeComment> findAllCommentsByNoticeId(Long noticeId);
}
