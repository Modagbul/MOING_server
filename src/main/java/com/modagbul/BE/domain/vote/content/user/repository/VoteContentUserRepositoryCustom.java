package com.modagbul.BE.domain.vote.content.user.repository;

import java.util.List;

public interface VoteContentUserRepositoryCustom {
    List<String> getUsersNickNameByContent(String content);
}
