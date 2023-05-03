package com.modagbul.BE.domain.notice.repository;

import com.modagbul.BE.domain.notice.entity.Notice;
import com.modagbul.BE.domain.notice.entity.QNotice;
import com.modagbul.BE.domain.notice_comment.entity.NoticeComment;
import com.modagbul.BE.domain.notice_comment.repsitory.NoticeCommentRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.modagbul.BE.domain.notice.entity.QNotice.notice;
import static com.modagbul.BE.domain.notice_comment.entity.QNoticeComment.noticeComment;

public class NoticeRepositoryCustomImpl implements NoticeRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public NoticeRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Optional<Notice> findNotDeletedByNoticeId(Long noticeId) {
        return Optional.ofNullable(queryFactory.selectFrom(notice)
                .where(notice.noticeId.eq(noticeId),
                        notice.isDeleted.eq(false))
                .fetchFirst());
    }
}
