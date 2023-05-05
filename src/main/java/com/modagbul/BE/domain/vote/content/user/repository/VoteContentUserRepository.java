package com.modagbul.BE.domain.vote.content.user.repository;

import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.vote.content.user.enttiy.VoteContentUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteContentUserRepository extends JpaRepository<VoteContentUser, Long>, VoteContentUserRepositoryCustom {
    void deleteAllByUser(User user);
}
