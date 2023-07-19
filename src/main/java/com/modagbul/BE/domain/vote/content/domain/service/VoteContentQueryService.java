package com.modagbul.BE.domain.vote.content.domain.service;

import com.modagbul.BE.domain.vote.board.domain.entity.Vote;
import com.modagbul.BE.domain.vote.board.exception.NotFoundVoteContentException;
import com.modagbul.BE.domain.vote.content.domain.entity.VoteContent;
import com.modagbul.BE.domain.vote.content.domain.repository.VoteContentRepository;
import com.modagbul.BE.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@DomainService
@RequiredArgsConstructor
@Transactional
public class VoteContentQueryService {

    private final VoteContentRepository voteContentRepository;

    public VoteContent getVoteContent(String content, Vote vote) {
        return voteContentRepository.findByContentAndVote(content, vote).orElseThrow(() -> new NotFoundVoteContentException());
    }
}
