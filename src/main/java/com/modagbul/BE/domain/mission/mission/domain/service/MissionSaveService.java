package com.modagbul.BE.domain.mission.domain.service;

import com.modagbul.BE.domain.mission.domain.entity.Mission;
import com.modagbul.BE.domain.mission.domain.repository.MissionRepository;
import lombok.RequiredArgsConstructor;

//@DomainService
@RequiredArgsConstructor
public class MissionSaveService {

    private final MissionRepository missionRepository;

    public Mission saveMission(Mission mission) {
        return missionRepository.save(mission);
    }
}
