package com.modagbul.BE.domain.vote.board.domain.repository;

import com.modagbul.BE.domain.vote.board.application.dto.res.QVoteResponse_GetVoteDetailsResponse;
import com.modagbul.BE.domain.vote.board.application.dto.res.VoteResponse.*;
import com.modagbul.BE.domain.vote.board.domain.entity.Vote;
import com.modagbul.BE.domain.vote.content.domain.entity.VoteContent;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.modagbul.BE.domain.vote.board.domain.entity.QVote.vote;
import static com.modagbul.BE.domain.vote.content.domain.entity.QVoteContent.voteContent;
import static com.modagbul.BE.domain.vote.content.user.enttiy.QVoteContentUser.voteContentUser;
import static com.modagbul.BE.domain.vote.read.domain.entity.QVoteRead.voteRead;

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

    @Override
    public GetVoteDetailsResponse findVoteByVoteId(Long voteId) {
        GetVoteDetailsResponse getVoteDetailsResponse = queryFactory
                .select(new QVoteResponse_GetVoteDetailsResponse(
                        vote.title,
                        vote.memo,
                        vote.createdDate,
                        vote.user.userId,
                        vote.user.nickName,
                        vote.user.imageUrl,
                        vote.isMultiple,
                        vote.isAnonymous))
                .distinct()
                .from(vote)
                .where(vote.voteId.eq(voteId))
                .fetchFirst();

        getVoteDetailsResponse.setVoteChoices(getVoteChoice(voteId));
        getVoteDetailsResponse.setNotReadUsersNickName(getNotReadUsersNickName(voteId));
        return getVoteDetailsResponse;
    }

    private List<String> getNotReadUsersNickName(Long voteId) {
        return queryFactory
                .select(voteRead.user.nickName)
                .from(voteRead)
                .where(voteRead.vote.voteId.eq(voteId))
                .where(voteRead.isRead.eq(false))
                .fetch();
    }

    private List<VoteChoice> getVoteChoice(Long voteId) {
        List<VoteChoice> voteChoiceList = new ArrayList<>();

        List<VoteContent> voteContents = queryFactory
                .selectFrom(voteContent)
                .where(voteContent.vote.voteId.eq(voteId))
                .fetch();

        voteContents.stream().forEach(voteContent -> {
            String content = voteContent.getContent();
            List<String> usersNickName = voteUserNickNameByContent(voteId, content);
            VoteChoice voteChoice = new VoteChoice(content, usersNickName.size(), usersNickName);
            voteChoiceList.add(voteChoice);
        });
        return voteChoiceList;
    }

    private List<String> voteUserNickNameByContent(Long voteId, String content) {
        return queryFactory
                .select(voteContentUser.nickName)
                .from(voteContentUser)
                .where(voteContentUser.vote.voteId.eq(voteId))
                .where(voteContentUser.content.eq(content))
                .fetch();
    }

    @Override
    public GetVoteAllResponse findVoteAllByTeamIdAndUseId(Long teamId, Long userId) {
        List<VoteBlock> voteBlocks = findVoteBlocksByTeamId(teamId, userId);

        Long notReadNum = queryFactory
                .from(vote)
                .join(vote.voteReads, voteRead)
                .where(vote.team.teamId.eq(teamId),
                        voteRead.user.userId.eq(userId),
                        voteRead.isRead.eq(false))
                .fetchCount();

        return new GetVoteAllResponse(notReadNum, voteBlocks);
    }

    @Override
    public List<GetUnReadVoteResponse> findUnReadVoteByTeamIdAndUserId(Long teamId, Long userId) {
        return queryFactory
                .select(Projections.constructor(GetUnReadVoteResponse.class,
                        vote.voteId, vote.title, vote.memo))
                .from(vote)
                .join(vote.voteReads, voteRead)
                .where(vote.team.teamId.eq(teamId),
                        vote.isClosed.eq(false),
                        voteRead.user.userId.eq(userId),
                        voteRead.isRead.eq(false))
                .orderBy(vote.createdDate.desc())
                .fetch();
    }

    private List<VoteBlock> findVoteBlocksByTeamId(Long teamId, Long userId) {
        return queryFactory
                .select(Projections.constructor(VoteBlock.class,
                        vote.voteId,
                        vote.title,
                        vote.memo,
                        vote.user.userId,
                        vote.user.nickName,
                        vote.user.imageUrl,
                        vote.voteComments.size(),
                        voteRead.isRead,
                        vote.createdDate))
                .distinct()
                .from(vote, voteRead)
                .join(vote.voteReads, voteRead)
                .where(vote.team.teamId.eq(teamId),
                        vote.isClosed.eq(false),
                        voteRead.user.userId.eq(userId))
                .orderBy(voteRead.isRead.asc())
                .orderBy(vote.createdDate.desc())
                .fetch();
    }

}
