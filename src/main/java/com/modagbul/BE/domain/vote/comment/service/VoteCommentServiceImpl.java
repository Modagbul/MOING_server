package com.modagbul.BE.domain.vote.comment.service;

import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.vote.board.entity.Vote;
import com.modagbul.BE.domain.vote.board.service.validate.VoteValidationService;
import com.modagbul.BE.domain.vote.comment.dto.VoteCommentDto;
import com.modagbul.BE.domain.vote.comment.dto.VoteCommentDto.CreateVoteCommentResponse;
import com.modagbul.BE.domain.vote.comment.dto.VoteCommentDto.GetVoteCommentResponse;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class VoteCommentServiceImpl implements VoteCommentService {

    private final VoteCommentMapper voteCommentMapper;
    private final VoteCommentRepository voteCommentRepository;

    private final VoteValidationService voteValidationService;


    @Override
    public CreateVoteCommentResponse createVoteComment(Long teamId, Long voteId, VoteCommentDto.CreateVoteCommentRequest createVoteCommentRequest) {
        VoteComment voteComment = voteCommentMapper.toEntity(teamId, voteId, createVoteCommentRequest);
        voteCommentRepository.save(voteComment);
        return new CreateVoteCommentResponse(voteComment.getVoteCommentId());
    }

    @Override
    public void deleteVoteComment(Long teamId, Long voteId, Long voteCommentId) {
        VoteComment voteComment = validateVoteComment(teamId, voteId, voteCommentId);
        validateUser(SecurityUtils.getLoggedInUser(), voteComment);
        voteComment.deleteVoteComment();
    }

    @Override
    public List<GetVoteCommentResponse> getAllVoteCommentByVoteId(Long teamId, Long voteId) {
        Vote vote = voteValidationService.validateVote(teamId, voteId);
        List<VoteComment> voteComments = voteCommentRepository.findAllVotesByVoteId(voteId);
        List<GetVoteCommentResponse> result = new ArrayList<>();
        Map<Long, GetVoteCommentResponse> map = new HashMap<>();
        voteComments.stream().forEach(c -> {
            GetVoteCommentResponse getVoteCommentResponse = voteCommentMapper.toDto(c);
            map.put(getVoteCommentResponse.getVoteCommentId(), getVoteCommentResponse);
            result.add(getVoteCommentResponse);
        });
        return result;
    }

    /**
     * VoteComment 유효성 체크하는 메서드
     *
     * @param teamId, voteId, voteCommentId
     * @return
     */
    @Override
    public VoteComment validateVoteComment(Long teamId, Long voteId, Long voteCommentId) {
        Vote vote = voteValidationService.validateVote(teamId, voteId);
        return this.voteCommentRepository.findNotDeletedByCommentId(voteCommentId).orElseThrow(() -> new NotFoundVoteCommentIdException());
    }

    /**
     * 댓글을 작성한 유저인지 확인하는 메서드
     *
     * @param user
     * @param voteComment
     */
    private void validateUser(User user, VoteComment voteComment) {
        if (voteComment.getUser().getUserId() != user.getUserId())
            throw new NotVoteCommentWriterException();
    }

}
