package com.modagbul.BE.domain.notice.board.service.create;

import com.modagbul.BE.domain.notice.board.dto.NoticeDto;

public interface NoticeCreationService {
    NoticeDto.CreateNoticeResponse createNotice(Long teamId, NoticeDto.CreateNoticeRequest createNoticeRequest);
}
