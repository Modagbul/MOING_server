package com.modagbul.BE.domain.vote.comment.dto;

import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.domain.vote.board.entity.Vote;
import com.modagbul.BE.domain.vote.board.service.validate.VoteValidationService;
import com.modagbul.BE.domain.vote.comment.dto.VoteCommentDto.GetVoteCommentResponse;
import com.modagbul.BE.domain.vote.comment.entity.VoteComment;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VoteCommentMapper {
    private final VoteValidationService voteValidationService;

    private final UserRepository userRepository;

    public VoteComment toEntity(Long teamId, Long voteId, VoteCommentDto.CreateVoteCommentRequest createVoteCommentRequest) {
        Vote vote = voteValidationService.validateVote(teamId, voteId);
        VoteComment voteComment = new VoteComment();

        voteComment.createVoteComment(createVoteCommentRequest.getContent());
        voteComment.setVote(vote);
        voteComment.setUser(userRepository.findById(SecurityUtils.getLoggedInUser().getUserId()).orElseThrow(() -> new NotFoundEmailException()));
        return voteComment;
    }

    public GetVoteCommentResponse toDto(VoteComment voteComment) {
        return new GetVoteCommentResponse(voteComment.getVoteCommentId(), voteComment.getContent(), voteComment.getUser().getUserId(), voteComment.getUser().getNickName(), voteComment.getUser().getImageUrl(), voteComment.getCreatedDate());
    }
}
