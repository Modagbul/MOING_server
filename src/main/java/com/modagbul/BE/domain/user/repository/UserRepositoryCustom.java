package com.modagbul.BE.domain.user.repository;

import com.modagbul.BE.domain.user.entity.User;

import java.util.Optional;

public interface UserRepositoryCustom {
    Optional<User> findByEmail(String email);
}
