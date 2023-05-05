package com.modagbul.BE.domain.vote.read.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.modagbul.BE.domain.vote.read.entity.QVoteRead.voteRead;

public class VoteReadRepositoryCustomImpl implements VoteReadRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public VoteReadRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<String> getNotReadUsersNickName(Long voteId) {
        return queryFactory
                .select(voteRead.user.nickName)
                .from(voteRead)
                .where(voteRead.vote.voteId.eq(voteId))
                .where(voteRead.isRead.eq(false))
                .fetch();
    }
}
