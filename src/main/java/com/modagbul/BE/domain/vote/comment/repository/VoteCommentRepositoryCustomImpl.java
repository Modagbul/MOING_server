package com.modagbul.BE.domain.vote.comment.repository;

import com.modagbul.BE.domain.vote.board.entity.Vote;
import com.modagbul.BE.domain.vote.comment.entity.QVoteComment;
import com.modagbul.BE.domain.vote.comment.entity.VoteComment;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.Optional;

import static com.modagbul.BE.domain.vote.comment.entity.QVoteComment.voteComment;

public class VoteCommentRepositoryCustomImpl implements VoteCommentRepositoryCustom{

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
}
