package com.modagbul.BE.domain.notice.board.application.mapper;

import com.modagbul.BE.domain.notice.board.application.dto.req.NoticeBoardRequest.CreateNoticeRequest;
import com.modagbul.BE.domain.notice.board.application.dto.res.NoticeBoardResponse.GetNoticeDetailsResponse;
import com.modagbul.BE.domain.notice.board.domain.entity.Notice;
import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.domain.team.exception.NotFoundTeamIdException;
import com.modagbul.BE.domain.team.domain.repository.TeamRepository;
import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class NoticeBoardMapper {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public Notice toEntity(Long teamId, CreateNoticeRequest createNoticeRequest) {
        Notice notice = new Notice();
        notice.createNotice(createNoticeRequest.getTitle(),
                createNoticeRequest.getContent());
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new NotFoundTeamIdException());
        notice.setUser(userRepository.findById(SecurityUtils.getLoggedInUser().getUserId()).orElseThrow(() -> new NotFoundEmailException()));
        notice.setTeam(team);
        return notice;
    }

    public GetNoticeDetailsResponse toDto(Notice notice, List<String> notReadUsersNickName) {
        GetNoticeDetailsResponse getNoticeDetailsResponse = new GetNoticeDetailsResponse(
                notice.getTitle(), notice.getContent(), notice.getCreatedDate(),
                notice.getUser().getUserId(), notice.getUser().getNickName(), notice.getUser().getImageUrl(),
                notReadUsersNickName
        );
        return getNoticeDetailsResponse;
    }

}
