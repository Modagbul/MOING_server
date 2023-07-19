package com.modagbul.BE.domain.vote.comment.application.service;

import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.vote.board.application.service.VoteValidateService;
import com.modagbul.BE.domain.vote.board.domain.entity.Vote;
import com.modagbul.BE.domain.vote.comment.domain.entity.VoteComment;
import com.modagbul.BE.domain.vote.comment.domain.service.VoteCommentQueryService;
import com.modagbul.BE.domain.vote.comment.exception.NotVoteCommentWriterException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteCommentValidateService {

    private final VoteValidateService voteValidateService;
    private final VoteCommentQueryService voteCommentQueryService;

    public VoteComment validateVoteComment(Long teamId, Long voteId, Long voteCommentId) {
        Vote vote = voteValidateService.validateVote(teamId, voteId);
        return voteCommentQueryService.getVoteComment(voteCommentId);
    }

    public void validateWriter(User user, VoteComment voteComment) {
        if (voteComment.getUser().getUserId() != user.getUserId())
            throw new NotVoteCommentWriterException();
    }
}
