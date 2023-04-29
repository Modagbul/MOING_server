package com.modagbul.BE.domain.user.repository;

import com.modagbul.BE.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    Optional<User> findByNickName(String nickName);
}
