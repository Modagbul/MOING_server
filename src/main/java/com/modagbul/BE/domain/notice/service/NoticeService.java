package com.modagbul.BE.domain.notice.service;

import com.modagbul.BE.domain.notice.dto.NoticeDto;
import com.modagbul.BE.domain.notice.dto.NoticeDto.CreateNoticeRequest;
import com.modagbul.BE.domain.notice.dto.NoticeDto.CreateNoticeResponse;

public interface NoticeService {
    CreateNoticeResponse createNotice(CreateNoticeRequest createNoticeRequest);
}
