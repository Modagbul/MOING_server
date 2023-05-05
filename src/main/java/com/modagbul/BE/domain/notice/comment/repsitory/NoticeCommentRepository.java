package com.modagbul.BE.domain.notice.comment.repsitory;

import com.modagbul.BE.domain.notice.comment.entity.NoticeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeCommentRepository extends JpaRepository<NoticeComment, Long> , NoticeCommentRepositoryCustom{
}
