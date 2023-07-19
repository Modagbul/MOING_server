package com.modagbul.BE.domain.vote.comment.domain.service;

import com.modagbul.BE.domain.vote.comment.domain.entity.VoteComment;
import com.modagbul.BE.domain.vote.comment.domain.repository.VoteCommentRepository;
import com.modagbul.BE.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@DomainService
@Transactional
@RequiredArgsConstructor
public class VoteCommentSaveService {
    private final VoteCommentRepository voteCommentRepository;

    public void saveVoteComment(VoteComment voteComment) {
        voteCommentRepository.save(voteComment);
    }

}
