package com.modagbul.BE.domain.notice.repository;

import com.modagbul.BE.domain.notice.entity.Notice;
import com.modagbul.BE.domain.notice_comment.repsitory.NoticeCommentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeRepositoryCustom {
}
