package com.modagbul.BE.domain.team.service;

import com.modagbul.BE.domain.team.repository.TeamRepository;
import com.modagbul.BE.domain.team.service.invitecode.TeamInvitationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.querydsl.core.types.ExpressionUtils.any;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class InvitationCodeGeneratorTest {
    @Mock
    TeamRepository teamRepository;

    @InjectMocks
    TeamInvitationService invitationCodeGenerator;

    @Test
    void 초대코드를_생성한다() {
        // Given
        given(teamRepository.findByInvitationCode(anyString())).willReturn(Optional.empty());

        // When
        String code = invitationCodeGenerator.generateCode();

        // Then
        assertNotNull(code);
        then(teamRepository).should(times(1)).findByInvitationCode(anyString());
    }
}