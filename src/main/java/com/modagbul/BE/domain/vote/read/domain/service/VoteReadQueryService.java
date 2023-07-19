package com.modagbul.BE.domain.vote.read.domain.service;

import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.vote.board.domain.entity.Vote;
import com.modagbul.BE.domain.vote.board.exception.NotFoundVoteUserException;
import com.modagbul.BE.domain.vote.read.domain.entity.VoteRead;
import com.modagbul.BE.domain.vote.read.domain.repository.VoteReadRepository;
import com.modagbul.BE.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@DomainService
@RequiredArgsConstructor
@Transactional
public class VoteReadQueryService {

    private final VoteReadRepository voteReadRepository;

    public VoteRead getVoteRead(User user, Vote vote) {
        return voteReadRepository.findByUserAndVote(user, vote).orElseThrow(() -> new NotFoundVoteUserException());
    }
}
