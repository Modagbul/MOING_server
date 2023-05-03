package com.modagbul.BE.domain.notice.service;

import com.modagbul.BE.domain.notice.dto.NoticeDto;
import com.modagbul.BE.domain.notice.dto.NoticeDto.CreateNoticeRequest;
import com.modagbul.BE.domain.notice.dto.NoticeDto.CreateNoticeResponse;
import com.modagbul.BE.domain.notice.dto.NoticeDto.DeleteNoticeRequest;
import com.modagbul.BE.domain.notice.entity.Notice;

public interface NoticeService {
    CreateNoticeResponse createNotice(CreateNoticeRequest createNoticeRequest);
    void deleteNotice(DeleteNoticeRequest deleteNoticeRequest);
    Notice validateNotice(Long noticeId);
}
