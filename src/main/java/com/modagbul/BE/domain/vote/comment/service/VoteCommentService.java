package com.modagbul.BE.domain.vote.comment.service;

import com.modagbul.BE.domain.vote.comment.dto.VoteCommentDto.CreateVoteCommentRequest;
import com.modagbul.BE.domain.vote.comment.dto.VoteCommentDto.CreateVoteCommentResponse;
import com.modagbul.BE.domain.vote.comment.dto.VoteCommentDto.GetVoteCommentResponse;
import com.modagbul.BE.domain.vote.comment.entity.VoteComment;

import java.util.List;

public interface VoteCommentService {
    CreateVoteCommentResponse createVoteComment(Long teamId, Long voteId, CreateVoteCommentRequest createVoteCommentRequest);

    void deleteVoteComment(Long teamId, Long voteId, Long voteCommentId);

    VoteComment validateVoteComment(Long teamId, Long voteId, Long voteCommentId);

    List<GetVoteCommentResponse> getAllVoteCommentByVoteId(Long teamId, Long voteId);
}
