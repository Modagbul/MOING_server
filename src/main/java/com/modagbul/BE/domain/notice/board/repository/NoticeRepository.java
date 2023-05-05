package com.modagbul.BE.domain.notice.board.repository;

import com.modagbul.BE.domain.notice.board.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long>, NoticeRepositoryCustom {
}
