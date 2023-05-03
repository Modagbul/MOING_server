package com.modagbul.BE.domain.user.repository;

import com.modagbul.BE.domain.user.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.modagbul.BE.domain.user.entity.QUser.user;

public class UserRepositoryCustomImpl implements UserRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public UserRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public Optional<User> findNotDeletedByEmail(String email) {
        return Optional.ofNullable(queryFactory.selectFrom(user)
                .where(user.email.eq(email),
                        user.isDeleted.eq(false))
                .fetchFirst());
    }

    @Override
    public Optional<User> findNotDeletedByNickName(String nickName) {
        return Optional.ofNullable(queryFactory.selectFrom(user)
                .where(user.nickName.eq(nickName),
                        user.isDeleted.eq(false))
                .fetchFirst());
    }
}
