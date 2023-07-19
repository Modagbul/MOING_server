package com.modagbul.BE.domain.vote.comment.application.service;

import com.modagbul.BE.domain.vote.board.application.service.VoteValidateService;
import com.modagbul.BE.domain.vote.board.domain.entity.Vote;
import com.modagbul.BE.domain.vote.comment.application.dto.res.VoteCommentResponse.GetVoteCommentResponse;
import com.modagbul.BE.domain.vote.comment.application.mapper.VoteCommentMapper;
import com.modagbul.BE.domain.vote.comment.domain.entity.VoteComment;
import com.modagbul.BE.domain.vote.comment.domain.service.VoteCommentQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteCommentReadService {
    private final VoteValidateService voteValidateService;
    private final VoteCommentMapper voteCommentMapper;
    private final VoteCommentQueryService voteCommentQueryService;

    public List<GetVoteCommentResponse> getAllVoteCommentByVoteId(Long teamId, Long voteId) {
        Vote vote = voteValidateService.validateVote(teamId, voteId);
        List<VoteComment> voteComments = voteCommentQueryService.getAllVoteCommentsByVoteId(voteId);
        List<GetVoteCommentResponse> result = new ArrayList<>();
        Map<Long, GetVoteCommentResponse> map = new HashMap<>();
        voteComments.stream().forEach(c -> {
            GetVoteCommentResponse getVoteCommentResponse = voteCommentMapper.toDto(c);
            map.put(getVoteCommentResponse.getVoteCommentId(), getVoteCommentResponse);
            result.add(getVoteCommentResponse);
        });
        return result;
    }
}
