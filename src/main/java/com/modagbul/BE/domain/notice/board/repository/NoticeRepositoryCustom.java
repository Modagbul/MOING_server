package com.modagbul.BE.domain.notice.board.repository;

import com.modagbul.BE.domain.notice.board.dto.NoticeDto;
import com.modagbul.BE.domain.notice.board.dto.NoticeDto.GetNoticeAllResponse;
import com.modagbul.BE.domain.notice.board.dto.NoticeDto.GetUnReadNoticeResponse;
import com.modagbul.BE.domain.notice.board.dto.NoticeDto.NoticeBlock;
import com.modagbul.BE.domain.notice.board.entity.Notice;

import java.util.List;
import java.util.Optional;

public interface NoticeRepositoryCustom {
    Optional<Notice> findNotDeletedByNoticeId(Long noticeId);
    GetNoticeAllResponse getNoticeAllByTeamId(Long teamId, Long userId);
    List<GetUnReadNoticeResponse> getUnReadNoticeByTeamId(Long teamId, Long userId);
}
