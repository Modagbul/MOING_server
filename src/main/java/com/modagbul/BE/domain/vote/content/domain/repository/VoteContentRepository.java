package com.modagbul.BE.domain.vote.content.domain.repository;

import com.modagbul.BE.domain.vote.board.domain.entity.Vote;
import com.modagbul.BE.domain.vote.content.domain.entity.VoteContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteContentRepository extends JpaRepository<VoteContent, Long> {
    Optional<VoteContent> findByContentAndVote(String content, Vote vote);
}
