package com.modagbul.BE.domain.vote.read.repository;

import java.util.List;

public interface VoteReadRepositoryCustom {
    List<String> getNotReadUsersNickName(Long voteId);
}
