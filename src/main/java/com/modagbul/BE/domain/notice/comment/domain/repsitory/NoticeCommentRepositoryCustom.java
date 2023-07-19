package com.modagbul.BE.domain.notice.comment.domain.repsitory;

import com.modagbul.BE.domain.notice.comment.domain.entity.NoticeComment;

import java.util.List;
import java.util.Optional;

public interface NoticeCommentRepositoryCustom {
    Optional<NoticeComment> findNoticeCommentByCommentId(Long commentId);
    List<NoticeComment> findNoticeCommentAllByNoticeId(Long noticeId);
}
