package com.modagbul.BE.domain.team_member.repository;

import com.modagbul.BE.domain.team_member.entity.QTeamMember;
import com.modagbul.BE.domain.team_member.entity.TeamMember;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

import java.util.List;

import static com.modagbul.BE.domain.team_member.entity.QTeamMember.teamMember;

public class TeamMemberRepositoryCustomImpl implements TeamMemberRepositoryCustom{
    private final JPAQueryFactory queryFactory;

    public TeamMemberRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<TeamMember> findByTeamId(Long teamId) {
        return queryFactory.selectFrom(teamMember)
                .where(teamMember.team.teamId.eq(teamId))
                .fetch();
    }
}
