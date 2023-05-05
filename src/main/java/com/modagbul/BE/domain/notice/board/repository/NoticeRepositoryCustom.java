package com.modagbul.BE.domain.notice.board.repository;

import com.modagbul.BE.domain.notice.board.entity.Notice;

import java.util.Optional;

public interface NoticeRepositoryCustom {
    Optional<Notice> findNotDeletedByNoticeId(Long noticeId);

}
