package com.modagbul.BE.domain.mission.repository;

import com.modagbul.BE.domain.mission.dto.MissionDetailDto;
import com.modagbul.BE.domain.mission.dto.MissionListDto;
import com.modagbul.BE.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("select m.team.leaderId from Mission m where m.missionId = :missionId and m.team.teamId = :teamId")
    Optional<Long> findLeaderIdByTeamIdAndMissionId( @Param("teamId") Long teamId, @Param("missionId") Long missionId);

    @Query("select m from Mission m where m.missionId = :missionId and m.team.teamId = :teamId")
    Optional<Mission> findByTeamIdAndMissionId( @Param("teamId") Long teamId, @Param("missionId") Long missionId);

    @Query(value = "select new com.modagbul.BE.domain.mission.dto.MissionListDto(m.missionId,m.title,m.dueTo)" +
            "from Mission m " +
            "where m.team.teamId = :teamId")
    Optional<List<MissionListDto>> findMissionListById(@Param("teamId") Long teamId);

    @Query(value=" select new com.modagbul.BE.domain.mission.dto.MissionDetailDto(m.title,m.dueTo,m.content,m.rule)" +
            "from Mission m  " +
            "where m.missionId = :missionId and m.team.teamId = :teamId ")
    Optional<MissionDetailDto> findMissionDetailById(@Param("teamId") Long teamId, @Param("missionId") Long missionId);


}
