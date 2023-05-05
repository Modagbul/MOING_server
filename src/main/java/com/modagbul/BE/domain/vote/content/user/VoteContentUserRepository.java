package com.modagbul.BE.domain.vote.content.user;

import com.modagbul.BE.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteContentUserRepository extends JpaRepository<VoteContentUser, Long> {
    void deleteAllByUser(User user);
}
