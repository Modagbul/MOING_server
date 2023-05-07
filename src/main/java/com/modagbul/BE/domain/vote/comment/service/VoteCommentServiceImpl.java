package com.modagbul.BE.domain.vote.comment.service;

import com.modagbul.BE.domain.notice.comment.dto.NoticeCommentDto;
import com.modagbul.BE.domain.notice.comment.entity.NoticeComment;
import com.modagbul.BE.domain.vote.comment.dto.VoteCommentDto;
import com.modagbul.BE.domain.vote.comment.dto.VoteCommentDto.CreateVoteCommentResponse;
import com.modagbul.BE.domain.vote.comment.dto.VoteCommentMapper;
import com.modagbul.BE.domain.vote.comment.entity.VoteComment;
import com.modagbul.BE.domain.vote.comment.repository.VoteCommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class VoteCommentServiceImpl implements VoteCommentService{

    private final VoteCommentMapper voteCommentMapper;
    private final VoteCommentRepository voteCommentRepository;


    @Override
    public CreateVoteCommentResponse createVoteComment(Long voteId, VoteCommentDto.CreateVoteCommentRequest createVoteCommentRequest) {
        VoteComment voteComment=voteCommentMapper.toEntity(voteId, createVoteCommentRequest);
        voteCommentRepository.save(voteComment);
        return new CreateVoteCommentResponse(voteComment.getVoteCommentId());
    }
}
