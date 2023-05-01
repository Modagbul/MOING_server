package com.modagbul.BE.domain.mission.repository;

import com.modagbul.BE.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("select m.team.leaderId from Mission m where m.missionId = :missionId and m.team.teamId = :teamId")
    Optional<Long> findLeaderIdByTeamIdAndMissionId( @Param("teamId") Long teamId, @Param("missionId") Long missionId);

    @Query("select m from Mission m where m.missionId = :missionId and m.team.teamId = :teamId")
    Optional<Mission> findByTeamIdAndMissionId( @Param("teamId") Long teamId, @Param("missionId") Long missionId);

}
