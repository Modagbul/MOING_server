package com.modagbul.BE.domain.vote.comment.service;

import com.modagbul.BE.domain.vote.comment.dto.VoteCommentDto.CreateVoteCommentRequest;
import com.modagbul.BE.domain.vote.comment.dto.VoteCommentDto.CreateVoteCommentResponse;
import com.modagbul.BE.domain.vote.comment.entity.VoteComment;

public interface VoteCommentService {
    CreateVoteCommentResponse createVoteComment(Long voteId, CreateVoteCommentRequest createVoteCommentRequest);
    void deleteVoteComment(Long voteCommentId);

    VoteComment validateVoteComment(Long voteCommentId);
}
