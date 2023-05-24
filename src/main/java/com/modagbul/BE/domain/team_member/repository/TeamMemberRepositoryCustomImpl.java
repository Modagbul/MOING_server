package com.modagbul.BE.domain.team_member.repository;

import com.modagbul.BE.domain.team_member.entity.TeamMember;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<List<String>> getFcmTokensByTeamId(Long teamId, Long userId) {
        return Optional.ofNullable(queryFactory.select(teamMember.user.fcmToken)
                .from(teamMember)
                .where(teamMember.team.teamId.eq(teamId)) //해당 소모임에 참여하고 있고
                .where(teamMember.user.isNewUploadPush.eq(true)) //알림 설정 on해 있고
                .where(teamMember.user.userId.ne(userId)) //지금 유저가 아닌 경우
                .fetch());
    }

}
