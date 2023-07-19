package com.modagbul.BE.domain.team.application.mapper;

import com.modagbul.BE.domain.team.application.dto.constant.Category;
import com.modagbul.BE.domain.team.application.dto.req.TeamRequest.CreateTeamRequest;
import com.modagbul.BE.domain.team.application.dto.res.TeamResponse.GetTeamInfo;
import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
@Component
public class TeamMapper {
    public Team toEntity(CreateTeamRequest createTeamRequest){
        Team team=new Team();
        team.createTeam(Enum.valueOf(Category.class, createTeamRequest.getCategory() ),
                createTeamRequest.getName(),
                createTeamRequest.getPersonnel(),
                LocalDate.parse(createTeamRequest.getStartDate()),
                createTeamRequest.getPeriod(),
                createTeamRequest.getInfo(),
                createTeamRequest.getPromise(),
                createTeamRequest.getProfileImg(),
                SecurityUtils.getLoggedInUser().getUserId());
        return team;
    }

    public GetTeamInfo toGetTeamInfo(Team team){
        return new GetTeamInfo(team.getName(),team.getEndDate().toString(),team.getProfileImg());
    }

}
