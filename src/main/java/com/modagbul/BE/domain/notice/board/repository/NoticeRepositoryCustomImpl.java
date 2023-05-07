package com.modagbul.BE.domain.notice.board.repository;

import com.modagbul.BE.domain.notice.board.dto.NoticeDto;
import com.modagbul.BE.domain.notice.board.dto.NoticeDto.GetNoticeAllResponse;
import com.modagbul.BE.domain.notice.board.dto.NoticeDto.GetUnReadNoticeResponse;
import com.modagbul.BE.domain.notice.board.dto.NoticeDto.NoticeBlock;
import com.modagbul.BE.domain.notice.board.entity.Notice;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.modagbul.BE.domain.notice.board.entity.QNotice.notice;
import static com.modagbul.BE.domain.notice.read.entity.QNoticeRead.noticeRead;

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

    @Override
    public GetNoticeAllResponse getNoticeAllByTeamId(Long teamId, Long userId) {
        List<NoticeBlock> noticeBlocks = findNoticeBlocksByTeamId(teamId, userId);

        Long notReadNum = queryFactory
                .from(notice)
                .join(notice.noticeReads, noticeRead)
                .where(notice.team.teamId.eq(teamId),
                        noticeRead.user.userId.eq(userId),
                        noticeRead.isRead.eq(false))
                .fetchCount();

        return new GetNoticeAllResponse(notReadNum, noticeBlocks);
    }

    @Override
    public List<GetUnReadNoticeResponse> getUnReadNoticeByTeamId(Long teamId, Long userId) {
        return queryFactory
                .select(Projections.constructor(GetUnReadNoticeResponse.class,
                        notice.title,notice.content))
                .from(notice)
                .join(notice.noticeReads, noticeRead)
                .where(notice.team.teamId.eq(teamId),
                        notice.isDeleted.eq(false),
                        noticeRead.user.userId.eq(userId),
                        noticeRead.isRead.eq(false))
                .orderBy(notice.createdDate.desc())
                .fetch();
    }


    private List<NoticeBlock> findNoticeBlocksByTeamId(Long teamId, Long userId) {
        return queryFactory
                .select(Projections.constructor(NoticeBlock.class,
                        notice.noticeId,
                        notice.title,
                        notice.content,
                        notice.user.userId,
                        notice.user.nickName,
                        notice.user.imageUrl,
                        notice.noticeComments.size(),
                        noticeRead.isRead,
                        notice.createdDate))
                .distinct()
                .from(notice, noticeRead)
                .join(notice.noticeReads, noticeRead)
                .where(notice.team.teamId.eq(teamId),
                        notice.isDeleted.eq(false),
                        noticeRead.user.userId.eq(userId))
                .orderBy(noticeRead.isRead.asc())
                .orderBy(notice.createdDate.desc())
                .fetch();
    }
}
