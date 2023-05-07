package com.modagbul.BE.domain.vote.comment.repository;

import com.modagbul.BE.domain.notice.comment.entity.NoticeComment;
import com.modagbul.BE.domain.vote.comment.entity.VoteComment;

import java.util.Optional;

public interface VoteCommentRepositoryCustom {
    Optional<VoteComment> findNotDeletedByCommentId(Long commentId);
}
