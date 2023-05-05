package com.modagbul.BE.domain.vote.content.user.repository;

import com.modagbul.BE.domain.vote.board.entity.Vote;

import java.util.List;

public interface VoteContentUserRepositoryCustom {
    List<String> getUsersNickNameByContent(Vote vote, String content);
}
