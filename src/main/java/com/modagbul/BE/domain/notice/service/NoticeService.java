package com.modagbul.BE.domain.notice.service;

import com.modagbul.BE.domain.notice.dto.NoticeDto.CreateNoticeRequest;
import com.modagbul.BE.domain.notice.dto.NoticeDto.CreateNoticeResponse;
import com.modagbul.BE.domain.notice.entity.Notice;

public interface NoticeService {
    CreateNoticeResponse createNotice(CreateNoticeRequest createNoticeRequest);
    void deleteNotice(Long noticeId);
    Notice validateNotice(Long noticeId);
}
