package com.modagbul.BE.domain.mission.domain.repository;

import com.modagbul.BE.domain.mission.application.dto.MissionDetailDto;
import com.modagbul.BE.domain.mission.application.dto.MissionListDto;
import com.modagbul.BE.domain.mission.domain.entity.Mission;
import com.modagbul.BE.domain.usermission.constant.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query("select m.team.leaderId from Mission m where m.missionId = :missionId and m.team.teamId = :teamId")
    Optional<Long> findLeaderIdByTeamIdAndMissionId(@Param("teamId") Long teamId, @Param("missionId") Long missionId);

    @Query("select m from Mission m where m.missionId = :missionId and m.team.teamId = :teamId")
    Optional<Mission> findByTeamIdAndMissionId(@Param("teamId") Long teamId, @Param("missionId") Long missionId);

    @Query(value = "select new com.modagbul.BE.domain.mission.dto.MissionListDto(m.missionId,m.title,m.dueTo,m.dueTo,um.status) " +
            "from UserMission um join um.mission m " +
            "where m.team.teamId = :teamId and um.user.userId = :userId and um.status = :status order by m.dueTo ")
    Optional<List<MissionListDto>> findIncompleteMissionListById(@Param("teamId") Long teamId, @Param("userId") Long userId, @Param("status") Status status);

    @Query(value = "select new com.modagbul.BE.domain.mission.dto.MissionListDto(m.missionId,m.title,m.dueTo,m.dueTo,um.status) " +
            "from UserMission um join um.mission m " +
            "where m.team.teamId = :teamId and um.user.userId = :userId and (um.status = :status or um.status = com.modagbul.BE.domain.usermission.constant.Status.PENDING)order by um.lastModifiedDate")
    Optional<List<MissionListDto>> findCompleteMissionListById(@Param("teamId") Long teamId, @Param("userId") Long userId, @Param("status") Status status);

    @Query(value = " select new com.modagbul.BE.domain.mission.dto.MissionDetailDto(m.title,m.dueTo,m.content,m.rule)" +
            "from Mission m  " +
            "where m.missionId = :missionId and m.team.teamId = :teamId ")
    Optional<MissionDetailDto> findMissionDetailById(@Param("teamId") Long teamId, @Param("missionId") Long missionId);

    @Query(value="select m from Mission m where  m.dueTo < :end and m.dueTo > :start")
    Optional<List<Mission>> findOneDayBeforeDueTo(@Param("start") String start, @Param("end") String end);


    @Query(value="select m from Mission m where m.team.teamId = :teamId")
    Optional<List<Mission>> findMissionsByTeamId(Long teamId);
}
