package com.modagbul.BE.domain.vote.comment.domain.service;

import com.modagbul.BE.domain.vote.comment.domain.entity.VoteComment;
import com.modagbul.BE.domain.vote.comment.domain.repository.VoteCommentRepository;
import com.modagbul.BE.domain.vote.comment.exception.NotFoundVoteCommentIdException;
import com.modagbul.BE.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

@DomainService
@RequiredArgsConstructor
@Transactional
public class VoteCommentQueryService {
    private final VoteCommentRepository voteCommentRepository;

    public List<VoteComment> getAllVoteCommentsByVoteId(Long voteId) {
        return voteCommentRepository.findVoteCommentAllByVoteId(voteId);
    }

    public VoteComment getVoteComment(Long voteCommentId) {
        return this.voteCommentRepository.findVoteCommentByCommentId(voteCommentId).orElseThrow(() -> new NotFoundVoteCommentIdException());
    }
}
