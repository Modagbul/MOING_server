package com.modagbul.BE.domain.vote.content.domain.service;

import com.modagbul.BE.domain.vote.content.domain.entity.VoteContent;
import com.modagbul.BE.domain.vote.content.domain.repository.VoteContentRepository;
import com.modagbul.BE.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@DomainService
@Transactional
@RequiredArgsConstructor
public class VoteContentSaveService {
    private final VoteContentRepository voteContentRepository;

    public void saveVoteContent(VoteContent voteContent){
        voteContentRepository.save(voteContent);
    }
}
