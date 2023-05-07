package com.modagbul.BE.domain.notice.board.service;

import com.modagbul.BE.domain.notice.board.dto.NoticeDto.CreateNoticeRequest;
import com.modagbul.BE.domain.notice.board.dto.NoticeDto.CreateNoticeResponse;
import com.modagbul.BE.domain.notice.board.dto.NoticeDto.GetNoticeDetailsResponse;
import com.modagbul.BE.domain.notice.board.entity.Notice;

public interface NoticeService {
    CreateNoticeResponse createNotice(Long teamId, CreateNoticeRequest createNoticeRequest);
    void deleteNotice(Long teamId, Long noticeId);
    Notice validateNotice(Long teamId, Long noticeId);
    GetNoticeDetailsResponse getNoticeDetails(Long teamId, Long noticeId);
}
