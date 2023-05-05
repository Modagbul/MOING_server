package com.modagbul.BE.domain.vote.content.repository;

import com.modagbul.BE.domain.vote.board.entity.Vote;
import com.modagbul.BE.domain.vote.content.entity.VoteContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.OpInc;

import java.util.Optional;

public interface VoteContentRepository extends JpaRepository<VoteContent, Long> {
    Optional<VoteContent> findByContentAndVote(String content, Vote vote);
}
