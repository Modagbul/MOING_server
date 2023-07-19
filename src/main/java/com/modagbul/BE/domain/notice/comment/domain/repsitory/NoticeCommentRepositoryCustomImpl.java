package com.modagbul.BE.domain.notice.comment.domain.repsitory;

import com.modagbul.BE.domain.notice.comment.domain.entity.NoticeComment;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.modagbul.BE.domain.notice.comment.domain.entity.QNoticeComment.noticeComment;


public class NoticeCommentRepositoryCustomImpl implements NoticeCommentRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public NoticeCommentRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<NoticeComment> findNoticeCommentByCommentId(Long commentId) {
        return Optional.ofNullable(queryFactory.selectFrom(noticeComment)
                .where(noticeComment.noticeCommentId.eq(commentId),
                        noticeComment.isDeleted.eq(false))
                .fetchFirst());
    }

    @Override
    public List<NoticeComment> findNoticeCommentAllByNoticeId(Long noticeId) {
        return queryFactory.selectFrom(noticeComment)
                .where(noticeComment.notice.noticeId.eq(noticeId),
                        noticeComment.isDeleted.eq(false))
                .orderBy(
                        noticeComment.createdDate.asc()
                )
                .fetch();
    }
}
