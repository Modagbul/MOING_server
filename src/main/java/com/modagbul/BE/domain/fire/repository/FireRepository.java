package com.modagbul.BE.domain.fire.repository;

import com.modagbul.BE.domain.fire.entity.Fire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FireRepository extends JpaRepository<Fire, Long> {

    @Query("select f.targetUserMission.userMissionId from Fire f where f.throwUser = :userId and f.targetUserMission.mission.missionId = :missionId")
    Optional<List<Long>> findFireByUserId(Long userId,Long missionId);

}
