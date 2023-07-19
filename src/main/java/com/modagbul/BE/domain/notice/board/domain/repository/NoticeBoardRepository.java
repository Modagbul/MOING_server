package com.modagbul.BE.domain.notice.board.domain.repository;

import com.modagbul.BE.domain.notice.board.domain.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeBoardRepository extends JpaRepository<Notice, Long>, NoticeBoardRepositoryCustom {
}
