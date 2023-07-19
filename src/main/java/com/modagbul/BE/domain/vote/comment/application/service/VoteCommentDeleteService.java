package com.modagbul.BE.domain.vote.comment.application.service;

import com.modagbul.BE.domain.vote.comment.domain.entity.VoteComment;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteCommentDeleteService {


    private final VoteCommentValidateService voteCommentValidateService;

    public void deleteVoteComment(Long teamId, Long voteId, Long voteCommentId) {
        VoteComment voteComment = voteCommentValidateService.validateVoteComment(teamId, voteId, voteCommentId);
        voteCommentValidateService.validateWriter(SecurityUtils.getLoggedInUser(), voteComment);
        voteComment.deleteVoteComment();
    }
}
