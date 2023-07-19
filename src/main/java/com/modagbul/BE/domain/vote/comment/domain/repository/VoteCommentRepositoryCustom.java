package com.modagbul.BE.domain.vote.comment.domain.repository;

import com.modagbul.BE.domain.vote.comment.domain.entity.VoteComment;

import java.util.List;
import java.util.Optional;

public interface VoteCommentRepositoryCustom {
    Optional<VoteComment> findVoteCommentByCommentId(Long commentId);

    List<VoteComment> findVoteCommentAllByVoteId(Long voteId);
}
