package com.modagbul.BE.domain.notice.board.domain.service;

import com.modagbul.BE.domain.notice.board.application.dto.res.NoticeBoardResponse;
import com.modagbul.BE.domain.notice.board.application.dto.res.NoticeBoardResponse.GetNoticeAllResponse;
import com.modagbul.BE.domain.notice.board.domain.entity.Notice;
import com.modagbul.BE.domain.notice.board.domain.repository.NoticeBoardRepository;
import com.modagbul.BE.domain.notice.board.exception.NotFoundNoticeIdException;
import com.modagbul.BE.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

@DomainService
@Transactional
@RequiredArgsConstructor
public class NoticeBoardQueryService {
    private final NoticeBoardRepository noticeBoardRepository;

    public Notice getNoticeByNoticeId(Long noticeId) {
        return noticeBoardRepository.findNoticeByNoticeId(noticeId).orElseThrow(() -> new NotFoundNoticeIdException());
    }

    public GetNoticeAllResponse getNoticeAllByTeamId(Long teamId, Long userId) {
        return noticeBoardRepository.findNoticeAllByTeamIdAndUserId(teamId, userId);
    }

    public List<NoticeBoardResponse.GetUnReadNoticeResponse> getUnReadNoticeByTeamId(Long teamId, Long userId) {
        return noticeBoardRepository.findUnReadNoticeByTeamIdAndUserId(teamId, userId);
    }
}
