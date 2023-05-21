package com.modagbul.BE.domain.team.service.invitecode;

import com.modagbul.BE.domain.team.dto.TeamDto;
import com.modagbul.BE.domain.team.dto.TeamDto.GetInviteCodeResponse;
import com.modagbul.BE.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;


public interface TeamInvitationService {

    String generateCode();

    GetInviteCodeResponse getInviteCode(Long teamId);


}
