package com.modagbul.BE.domain.vote.comment.service;

import com.modagbul.BE.domain.vote.comment.dto.VoteCommentDto.CreateVoteCommentRequest;
import com.modagbul.BE.domain.vote.comment.dto.VoteCommentDto.CreateVoteCommentResponse;

public interface VoteCommentService {
    CreateVoteCommentResponse createVoteComment(Long voteId, CreateVoteCommentRequest createVoteCommentRequest);
}
