package com.modagbul.BE.domain.vote.board.service.alarm;

import com.modagbul.BE.domain.vote.board.entity.Vote;

public interface VoteAlarmService {
    void sendNewUploadVoteAlarm(Vote vote, Long teamId, Long userId);
}
