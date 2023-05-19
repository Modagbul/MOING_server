package com.modagbul.BE.domain.vote.comment.repository;

import com.modagbul.BE.domain.vote.comment.entity.VoteComment;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.modagbul.BE.domain.vote.comment.entity.QVoteComment.voteComment;

public class VoteCommentRepositoryCustomImpl implements VoteCommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public VoteCommentRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Optional<VoteComment> findNotDeletedByCommentId(Long commentId) {
        return Optional.ofNullable(queryFactory.selectFrom(voteComment)
                .where(voteComment.voteCommentId.eq(commentId))
                .where(voteComment.isDeleted.eq(false))
                .fetchFirst());
    }

    @Override
    public List<VoteComment> findAllVotesByVoteId(Long voteId) {
        return queryFactory.selectFrom(voteComment)
                .where(voteComment.vote.voteId.eq(voteId),
                        voteComment.isDeleted.eq(false))
                .orderBy(
                        voteComment.createdDate.asc()
                )
                .fetch();
    }
}
