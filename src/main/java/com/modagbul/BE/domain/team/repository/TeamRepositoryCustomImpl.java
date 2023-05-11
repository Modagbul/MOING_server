package com.modagbul.BE.domain.team.repository;

import com.modagbul.BE.domain.team.dto.TeamDto.GetTeamResponse;
import com.modagbul.BE.domain.team.dto.TeamDto.TeamBlock;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static com.modagbul.BE.domain.team.entity.QTeam.team;
import static com.modagbul.BE.domain.team_member.entity.QTeamMember.teamMember;

public class TeamRepositoryCustomImpl implements TeamRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public TeamRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public GetTeamResponse getTeam(Long userId) {
        List<TeamBlock> teamBlocks = getTeamBlock(userId);
        Long inProgressNum = queryFactory.select(team)
                .from(team)
                .join(team.teamMembers, teamMember)
                .where(teamMember.user.userId.eq(userId))
                .where(team.approvalStatus.eq(true))
                .where(team.startDate.loe(LocalDate.now()))
                .where(team.endDate.goe(LocalDate.now()))
                .fetchCount();
        return new GetTeamResponse(inProgressNum, teamBlocks);
    }

    private List<TeamBlock> getTeamBlock(Long userId){
        return queryFactory.select(Projections.constructor(TeamBlock.class,
                team.teamId, team.name, team.personnel, team.startDate, team.endDate, team.profileImg, team.approvalStatus))
                .from(team)
                .join(team.teamMembers, teamMember)
                .where(team.startDate.loe(LocalDate.now()))
                .where(team.endDate.goe(LocalDate.now()))
                .where(teamMember.user.userId.eq(userId))
                .orderBy(team.startDate.asc())
                .fetch();
    }
}
