package com.modagbul.BE.domain.notice.board.dto;

import com.modagbul.BE.domain.notice.board.dto.NoticeDto.CreateNoticeRequest;
import com.modagbul.BE.domain.notice.board.dto.NoticeDto.GetNoticeDetailsResponse;
import com.modagbul.BE.domain.notice.board.entity.Notice;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.team.exception.NotFoundTeamIdException;
import com.modagbul.BE.domain.team.repository.TeamRepository;
import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class NoticeMapper {

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
