package com.modagbul.BE.domain.usermission.repository;

import com.modagbul.BE.domain.mission.entity.Mission;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.usermission.constant.Status;
import com.modagbul.BE.domain.usermission.dto.UserMissionDetailDto;
import com.modagbul.BE.domain.usermission.dto.UserMissionListDto;
import com.modagbul.BE.domain.usermission.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {


    @Query(value = " select new com.modagbul.BE.domain.usermission.dto.UserMissionDetailDto(um.mission.title,um.mission.dueTo,um.mission.content,um.mission.rule,um.status,um.achieve)" +
            "from UserMission um " +
            "where um.team.teamId = :teamId and um.user.userId = :userId and um.mission.missionId = :missionId")
    Optional<UserMissionDetailDto> findUserMissionDetailById(@Param("teamId") Long teamId, @Param("userId") Long userId, @Param("missionId") Long missionId);




    @Query(value = "select new com.modagbul.BE.domain.usermission.dto.UserMissionListDto(" +
            "um.userMissionId,um.user.nickName,um.user.imageUrl,um.status,um.achieve,um.lastModifiedDate) " +
            "from UserMission um " +
            "where um.team.teamId = :teamId and um.mission.missionId = :missionId and um.status = :status order by um.lastModifiedDate")
    Optional<List<UserMissionListDto>> findCompleteUserMissionListById(@Param("teamId") Long teamId, @Param("missionId") Long missionId, @Param("status") Status status);

   @Query(value = "select new com.modagbul.BE.domain.usermission.dto.UserMissionListDto(" +
            "um.userMissionId,um.user.nickName,um.user.imageUrl,um.status,um.achieve,um.lastModifiedDate) " +
            "from UserMission um " +
            "where um.team.teamId = :teamId and um.mission.missionId = :missionId and um.status = :status order by um.user.nickName")
    Optional<List<UserMissionListDto>> findInCompleteUserMissionListById(@Param("teamId") Long teamId, @Param("missionId") Long missionId, @Param("status") Status status);

    @Query("select um from UserMission um where um.user.userId = :userId and um.team.teamId = :teamId and um.mission.missionId = :missionId")
    Optional<UserMission> findUserMissionById(@Param("userId") Long userId, @Param("teamId") Long teamId, @Param("missionId") Long missionId);


    //    @Query(value = "select count(um.userMissionId) / (select count(m.missionId) from Mission m where m.team.teamId = :teamId) " +
//            "from UserMission um " +
//            "where um.team.teamId = :teamId and um.user.userId = :userId " +
//            "and um.status = com.modagbul.BE.domain.usermission.constant.Status.COMPLETE", nativeQuery = true)
//
    @Query(value = "select count(um.user_mission_id) / (select count(m.mission_id) from mission m where m.team_id = :teamId) * 100 from user_mission um where um.team_id = :teamId and um.user_id = :userId and um.status = 'COMPLETE'", nativeQuery = true)
    Optional<Long> getPersonalRateForGraphById(@Param("userId") Long userId, @Param("teamId") Long teamId);

    @Query(value = "select um.user from UserMission um where um.mission = :mission and um.status = com.modagbul.BE.domain.usermission.constant.Status.INCOMPLETE")
    Optional<List<User>> getInCompleteUsersByMission(@Param("mission") Mission mission);
}
