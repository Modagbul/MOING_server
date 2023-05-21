package com.modagbul.BE.domain.team.service.invitecode;

import com.modagbul.BE.domain.team.dto.TeamDto.GetInviteCodeResponse;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.team.repository.TeamRepository;
import com.modagbul.BE.domain.team.service.validate.TeamValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamInvitationServiceImpl implements TeamInvitationService {
    private final TeamRepository teamRepository;
    private final TeamValidationService teamValidationService;

    @Override
    public String generateCode() {
        String code;
        do {
            code= UUID.randomUUID().toString();
        } while(isDuplicatd(code));
        return code;
    }

    @Override
    public GetInviteCodeResponse getInviteCode(Long teamId) {
        Team team=teamValidationService.validateTeam(teamId);
        teamValidationService.checkLeader(team);
        return new GetInviteCodeResponse(team.getInvitationCode());
    }

    private boolean isDuplicatd(String encodedCode){
        if( teamRepository.findByInvitationCode(encodedCode).isPresent())
            return true;
        else return false;
    }
}
