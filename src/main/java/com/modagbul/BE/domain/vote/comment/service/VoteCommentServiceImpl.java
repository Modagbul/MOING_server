package com.modagbul.BE.domain.vote.comment.service;

import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.vote.comment.dto.VoteCommentDto;
import com.modagbul.BE.domain.vote.comment.dto.VoteCommentDto.CreateVoteCommentResponse;
import com.modagbul.BE.domain.vote.comment.dto.VoteCommentMapper;
import com.modagbul.BE.domain.vote.comment.entity.VoteComment;
import com.modagbul.BE.domain.vote.comment.exception.NotFoundVoteCommentIdException;
import com.modagbul.BE.domain.vote.comment.exception.NotVoteCommentWriterException;
import com.modagbul.BE.domain.vote.comment.repository.VoteCommentRepository;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
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

    @Override
    public void deleteVoteComment(Long voteCommentId) {
        VoteComment voteComment=validateVoteComment(voteCommentId);
        validateUser(SecurityUtils.getLoggedInUser(), voteComment);
        voteComment.deleteVoteComment();
    }

    /**
     * VoteComment 유효성 체크하는 메서드
     * @param voteCommentId
     * @return
     */
    @Override
    public VoteComment validateVoteComment(Long voteCommentId) {
        return this.voteCommentRepository.findNotDeletedByCommentId(voteCommentId).orElseThrow(()->new NotFoundVoteCommentIdException());
    }

    /**
     * 댓글을 작성한 유저인지 확인하는 메서드
     * @param user
     * @param voteComment
     */
    private void validateUser(User user, VoteComment voteComment){
        if(voteComment.getUser().getUserId()!=user.getUserId())
            throw new NotVoteCommentWriterException();
    }

}
