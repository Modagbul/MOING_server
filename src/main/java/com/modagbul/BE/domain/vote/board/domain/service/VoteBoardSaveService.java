package com.modagbul.BE.domain.vote.board.domain.service;

import com.modagbul.BE.domain.vote.board.domain.entity.Vote;
import com.modagbul.BE.domain.vote.board.domain.repository.VoteRepository;
import com.modagbul.BE.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@DomainService
@RequiredArgsConstructor
@Transactional
public class VoteBoardSaveService {

    private final VoteRepository voteRepository;

    public void saveVote(Vote vote){
        voteRepository.save(vote);
    }
}
