package com.modagbul.BE.domain.notice.board.application.service;

import com.modagbul.BE.domain.notice.board.domain.entity.Notice;
import com.modagbul.BE.domain.notice.board.domain.service.NoticeBoardQueryService;
import com.modagbul.BE.domain.notice.board.exception.NotNoticeWriterException;
import com.modagbul.BE.domain.team.application.service.TeamValidateService;
import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeBoardValidateService {

    private final TeamValidateService teamValidateService;
    private final NoticeBoardQueryService noticeBoardQueryService;

    /**
     * 공지가 삭제되었는지 확인하는 메서드: 유효성 체크
     *
     * @param noticeId
     * @return 공지가 삭제되지 않았다면 Notice 반환
     */

    public Notice validateNotice(Long teamId, Long noticeId) {
        Team team = teamValidateService.validateTeam(teamId);
        return noticeBoardQueryService.getNoticeByNoticeId(noticeId);
    }

    /**
     * 공지를 작성한 유저인지 확인하는 메서드
     * @param user
     * @param notice
     */


    public void validateWriter(User user, Notice notice){
        if(notice.getUser().getUserId()!=user.getUserId())
            throw new NotNoticeWriterException();
    }
}
