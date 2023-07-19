package com.modagbul.BE.domain.team.domain.repository;

import com.modagbul.BE.domain.team.application.dto.res.TeamResponse.GetTeamResponse;
import com.modagbul.BE.domain.team.application.dto.res.TeamResponse.TeamBlock;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static com.modagbul.BE.domain.team.domain.entity.QTeam.team;
import static com.modagbul.BE.domain.team_member.domain.entity.QTeamMember.teamMember;


public class TeamRepositoryCustomImpl implements TeamRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public TeamRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public GetTeamResponse findTeamByUserId(Long userId) {
        LocalDate now = LocalDate.now(ZoneId.of("Asia/Seoul"));

        List<TeamBlock> teamBlocks = getTeamBlock(userId);
        Long inProgressNum = queryFactory.select(team)
                .from(team)
                .join(team.teamMembers, teamMember)
                .where(teamMember.user.userId.eq(userId))
                .where(team.approvalStatus.eq(true))
                .where(team.startDate.loe(now))
                .where(team.endDate.goe(now))
                .fetchCount();
        return new GetTeamResponse(inProgressNum, teamBlocks);
    }

    private List<TeamBlock> getTeamBlock(Long userId){
        LocalDate now = LocalDate.now(ZoneId.of("Asia/Seoul"));

        return queryFactory.select(Projections.constructor(TeamBlock.class,
                team.teamId, team.name, team.personnel, team.startDate, team.endDate, team.profileImg, team.approvalStatus))
                .from(team)
                .join(team.teamMembers, teamMember)
                .where(team.startDate.loe(now))
                .where(team.endDate.goe(now))
                .where(teamMember.user.userId.eq(userId))
                .orderBy(team.startDate.asc())
                .fetch();
    }

    public Long findLeaderIdByTeamId(Long teamId) {
        return queryFactory.select(team.leaderId)
                .from(team)
                .where(team.teamId.eq(teamId))
                .fetchOne();

    }
}
