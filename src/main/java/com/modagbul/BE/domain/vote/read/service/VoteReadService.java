package com.modagbul.BE.domain.vote.read.service;

import com.modagbul.BE.domain.vote.board.entity.Vote;

public interface VoteReadService {
    void createVoteRead(Long teamId, Vote vote);
    void updateVoteRead(Vote vote);
}
