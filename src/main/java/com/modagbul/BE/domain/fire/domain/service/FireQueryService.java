package com.modagbul.BE.domain.fire.domain.service;

import com.modagbul.BE.domain.fire.domain.entity.Fire;
import com.modagbul.BE.domain.fire.domain.repository.FireRepository;
import com.modagbul.BE.domain.usermission.exception.NotFoundUserMissionsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FireQueryService {

    private final FireRepository fireRepository;

    public List<Long> getFireById(Long userId, Long missionId) {
        return fireRepository.findFireByUserId(userId,missionId).orElseThrow(NotFoundUserMissionsException::new);
    }
}
