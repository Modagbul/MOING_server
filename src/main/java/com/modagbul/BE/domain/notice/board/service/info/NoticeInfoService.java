package com.modagbul.BE.domain.notice.board.service.info;

import com.modagbul.BE.domain.notice.board.dto.NoticeDto;

import java.util.List;

public interface NoticeInfoService {
    NoticeDto.GetNoticeDetailsResponse getNoticeDetails(Long teamId, Long noticeId); //info
    NoticeDto.GetNoticeAllResponse getNoticeAll(Long teamId); //info
    List<NoticeDto.GetUnReadNoticeResponse> getUnReadNotice(Long teamId); //info
}
