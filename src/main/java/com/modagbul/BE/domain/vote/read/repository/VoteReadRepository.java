package com.modagbul.BE.domain.vote.read.repository;

import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.vote.board.entity.Vote;
import com.modagbul.BE.domain.vote.read.entity.VoteRead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteReadRepository extends JpaRepository<VoteRead, Long> {

    Optional<VoteRead> findByUserAndVote(User user, Vote vote);
}
