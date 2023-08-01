package com.modagbul.BE.domain.mission.main.domain.service;

import com.modagbul.BE.domain.mission.main.domain.entity.Mission;
import com.modagbul.BE.domain.mission.main.domain.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MissionSaveService {

    private final MissionRepository missionRepository;

    public Mission saveMission(Mission mission) {
        return missionRepository.save(mission);
    }
}
