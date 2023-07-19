package com.modagbul.BE.domain.notice.comment.domain.repsitory;

import com.modagbul.BE.domain.notice.comment.domain.entity.NoticeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeCommentRepository extends JpaRepository<NoticeComment, Long> , NoticeCommentRepositoryCustom{
}
