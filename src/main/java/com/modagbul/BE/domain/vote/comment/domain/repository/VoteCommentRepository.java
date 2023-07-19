package com.modagbul.BE.domain.vote.comment.domain.repository;

import com.modagbul.BE.domain.vote.comment.domain.entity.VoteComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteCommentRepository extends JpaRepository<VoteComment, Long>, VoteCommentRepositoryCustom {
}
