package com.modagbul.BE.domain.vote.comment.application.service;

import com.modagbul.BE.domain.vote.comment.application.dto.req.VoteCommentRequest.CreateVoteCommentRequest;
import com.modagbul.BE.domain.vote.comment.application.dto.res.VoteCommentResponse.CreateVoteCommentResponse;
import com.modagbul.BE.domain.vote.comment.application.mapper.VoteCommentMapper;
import com.modagbul.BE.domain.vote.comment.domain.entity.VoteComment;
import com.modagbul.BE.domain.vote.comment.domain.service.VoteCommentSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class VoteCommentCreateService {
    private final VoteCommentMapper voteCommentMapper;
    private final VoteCommentSaveService voteCommentSaveService;

    public CreateVoteCommentResponse createVoteComment(Long teamId, Long voteId, CreateVoteCommentRequest createVoteCommentRequest) {
        VoteComment voteComment = voteCommentMapper.toEntity(teamId, voteId, createVoteCommentRequest);
        voteCommentSaveService.saveVoteComment(voteComment);
        return new CreateVoteCommentResponse(voteComment.getVoteCommentId());
    }
}
