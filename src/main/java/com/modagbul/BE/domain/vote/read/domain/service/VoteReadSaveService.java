package com.modagbul.BE.domain.vote.read.domain.service;

import com.modagbul.BE.domain.vote.read.domain.entity.VoteRead;
import com.modagbul.BE.domain.vote.read.domain.repository.VoteReadRepository;
import com.modagbul.BE.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@DomainService
@RequiredArgsConstructor
@Transactional
public class VoteReadSaveService {
    private final VoteReadRepository voteReadRepository;
    public void saveVoteRead(VoteRead voteRead){
        voteReadRepository.save(voteRead);
    }
}
