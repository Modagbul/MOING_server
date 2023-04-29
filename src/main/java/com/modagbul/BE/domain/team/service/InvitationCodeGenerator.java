package com.modagbul.BE.domain.team.service;

import com.modagbul.BE.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class InvitationCodeGenerator {

    private final PasswordEncoder passwordEncoder;
    private final TeamRepository teamRepository;

    public String generateCode() {
        String code;
        do {
            code=UUID.randomUUID().toString();
        } while(isDuplicatd(code));
        return code;
    }

    private boolean isDuplicatd(String encodedCode){
        if( teamRepository.findByInvitationCode(encodedCode).isPresent())
            return true;
        else return false;
    }

}
