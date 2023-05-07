package com.modagbul.BE.domain.notice.board.service;

import com.modagbul.BE.domain.notice.board.dto.NoticeDto;
import com.modagbul.BE.domain.notice.board.dto.NoticeDto.*;
import com.modagbul.BE.domain.notice.board.entity.Notice;

import java.util.List;

public interface NoticeService {
    CreateNoticeResponse createNotice(Long teamId, CreateNoticeRequest createNoticeRequest);
    void deleteNotice(Long teamId, Long noticeId);
    Notice validateNotice(Long teamId, Long noticeId);
    GetNoticeDetailsResponse getNoticeDetails(Long teamId, Long noticeId);
    GetNoticeAllResponse getNoticeAll(Long teamId);
    List<GetUnReadNoticeResponse> getUnReadNotice(Long teamId);
}
