package com.modagbul.BE.domain.notice_comment.repsitory;

import com.modagbul.BE.domain.notice_comment.entity.NoticeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeCommentRepsitory extends JpaRepository<NoticeComment, Long> , NoticeCommentRepositoryCustom{
}
