package com.modagbul.BE.domain.vote.content.repository;

import com.modagbul.BE.domain.vote.content.entity.VoteContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteContentRepository extends JpaRepository<VoteContent, Long> {
}
