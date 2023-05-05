package com.modagbul.BE.domain.vote.board.repository;

import com.modagbul.BE.domain.vote.board.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long>, VoteRepositoryCustom {
}
