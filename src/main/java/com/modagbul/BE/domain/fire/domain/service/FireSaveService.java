package com.modagbul.BE.domain.fire.domain.service;

import com.modagbul.BE.domain.fire.domain.entity.Fire;
import com.modagbul.BE.domain.fire.domain.repository.FireRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FireSaveService {

    private final FireRepository fireRepository;

    public Fire saveFire(Fire fire) {
        return fireRepository.save(fire);
    }

}
