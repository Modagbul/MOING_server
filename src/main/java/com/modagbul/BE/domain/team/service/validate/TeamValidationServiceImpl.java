package com.modagbul.BE.domain.team.service.validate;

import com.modagbul.BE.domain.team.dto.TeamDto;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.team.exception.AccessException;
import com.modagbul.BE.domain.team.exception.NotFoundTeamIdException;
import com.modagbul.BE.domain.team.repository.TeamRepository;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.modagbul.BE.domain.team.constant.TeamConstant.TeamServiceMessage.EXISTED_TEAMNAME;
import static com.modagbul.BE.domain.team.constant.TeamConstant.TeamServiceMessage.VALID_TEAMNAME;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamValidationServiceImpl implements TeamValidationService{

    private final TeamRepository teamRepository;

    @Override
    public Team validateTeam(Long teamId) {
        return teamRepository.findById(teamId)
                .orElseThrow(() -> new NotFoundTeamIdException());
    }

    @Override
    public void checkLeader(Team team){
        if( team.getLeaderId() != SecurityUtils.getLoggedInUser().getUserId() )
            throw new AccessException();
    }

    @Override
    public TeamDto.CheckTeamNameResponse checkTeamName(String teamName) {
        if(teamRepository.findByName(teamName).isPresent()){
            return new TeamDto.CheckTeamNameResponse(EXISTED_TEAMNAME.getValue());
        }else{
            return new TeamDto.CheckTeamNameResponse(VALID_TEAMNAME.getValue());
        }
    }
}
