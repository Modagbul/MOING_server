package com.modagbul.BE.domain.notice.board.domain.repository;

import com.modagbul.BE.domain.notice.board.application.dto.res.NoticeBoardResponse.GetNoticeAllResponse;
import com.modagbul.BE.domain.notice.board.application.dto.res.NoticeBoardResponse.GetUnReadNoticeResponse;
import com.modagbul.BE.domain.notice.board.domain.entity.Notice;

import java.util.List;
import java.util.Optional;

public interface NoticeBoardRepositoryCustom {
    Optional<Notice> findNoticeByNoticeId(Long noticeId);
    GetNoticeAllResponse findNoticeAllByTeamIdAndUserId(Long teamId, Long userId);
    List<GetUnReadNoticeResponse> findUnReadNoticeByTeamIdAndUserId(Long teamId, Long userId);
}
