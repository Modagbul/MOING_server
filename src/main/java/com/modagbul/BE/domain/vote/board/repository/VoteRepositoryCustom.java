package com.modagbul.BE.domain.vote.board.repository;

import com.modagbul.BE.domain.vote.board.entity.Vote;

import java.util.Optional;

public interface VoteRepositoryCustom {
    Optional<Vote> findNotDeletedByVoteId(Long voteId);
}
