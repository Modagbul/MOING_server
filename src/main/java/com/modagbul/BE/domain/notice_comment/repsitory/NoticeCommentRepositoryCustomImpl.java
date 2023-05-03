package com.modagbul.BE.domain.notice_comment.repsitory;

import com.modagbul.BE.domain.notice_comment.entity.NoticeComment;
import com.modagbul.BE.domain.notice_comment.entity.QNoticeComment;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.modagbul.BE.domain.notice_comment.entity.QNoticeComment.noticeComment;

public class NoticeCommentRepositoryCustomImpl implements NoticeCommentRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public NoticeCommentRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<NoticeComment> findNotDeletedByCommentId(Long commentId) {
        return Optional.ofNullable(queryFactory.selectFrom(noticeComment)
                .where(noticeComment.noticeCommentId.eq(commentId),
                        noticeComment.isDeleted.eq(false))
                .fetchFirst());
    }

    @Override
    public List<NoticeComment> findAllCommentsByNoticeId(Long noticeId) {
        return queryFactory.selectFrom(noticeComment)
                .where(noticeComment.notice.noticeId.eq(noticeId),
                        noticeComment.isDeleted.eq(false))
                .orderBy(
                        noticeComment.createdDate.desc()
                )
                .fetch();
    }
}
