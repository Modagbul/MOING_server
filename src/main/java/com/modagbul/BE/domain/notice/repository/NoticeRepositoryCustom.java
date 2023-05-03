package com.modagbul.BE.domain.notice.repository;

import com.modagbul.BE.domain.notice.entity.Notice;
import com.modagbul.BE.domain.notice_comment.entity.NoticeComment;

import java.util.Optional;

public interface NoticeRepositoryCustom {
    Optional<Notice> findNotDeletedByNoticeId(Long noticeId);

}
