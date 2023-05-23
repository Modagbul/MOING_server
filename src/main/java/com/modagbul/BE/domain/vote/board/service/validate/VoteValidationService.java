package com.modagbul.BE.domain.vote.board.service.validate;

import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.vote.board.entity.Vote;

public interface VoteValidationService {
    Vote validateVote(Long teamId, Long voteId);
    void validateWriter(User user, Vote vote);
}
