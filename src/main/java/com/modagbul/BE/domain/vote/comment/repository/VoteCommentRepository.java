package com.modagbul.BE.domain.vote.comment.repository;

import com.modagbul.BE.domain.vote.comment.entity.VoteComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteCommentRepository extends JpaRepository<VoteComment, Long>, VoteCommentRepositoryCustom {
}
