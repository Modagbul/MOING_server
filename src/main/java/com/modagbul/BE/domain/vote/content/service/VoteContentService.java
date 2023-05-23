package com.modagbul.BE.domain.vote.content.service;

import com.modagbul.BE.domain.vote.board.dto.VoteDto;
import com.modagbul.BE.domain.vote.board.entity.Vote;

public interface VoteContentService {
    void createVoteContent(VoteDto.CreateVoteRequest createVoteRequest, Vote vote);
    void updateVoteContent(VoteDto.DoVoteRequest doVoteRequest, Vote vote);
}
