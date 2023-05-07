package com.modagbul.BE.domain.vote.comment.repository;

import com.modagbul.BE.domain.vote.comment.entity.VoteComment;

import java.util.List;
import java.util.Optional;

public interface VoteCommentRepositoryCustom {
    Optional<VoteComment> findNotDeletedByCommentId(Long commentId);

    List<VoteComment> findAllVotesByVoteId(Long voteId);
}
