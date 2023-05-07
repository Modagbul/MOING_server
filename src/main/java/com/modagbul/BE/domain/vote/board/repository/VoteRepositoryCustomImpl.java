package com.modagbul.BE.domain.vote.board.repository;

import com.modagbul.BE.domain.vote.board.entity.Vote;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.modagbul.BE.domain.vote.board.entity.QVote.vote;

public class VoteRepositoryCustomImpl implements VoteRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public VoteRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Vote> findNotClosedByVoteId(Long voteId) {
        return Optional.ofNullable(queryFactory.selectFrom(vote)
                .where(vote.voteId.eq(voteId),
                        vote.isClosed.eq(false))
                .fetchFirst());
    }
}
