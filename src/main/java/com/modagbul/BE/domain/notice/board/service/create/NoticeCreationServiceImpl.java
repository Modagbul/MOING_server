package com.modagbul.BE.domain.notice.board.service.create;

import com.modagbul.BE.domain.notice.board.dto.NoticeDto;
import com.modagbul.BE.domain.notice.board.dto.NoticeMapper;
import com.modagbul.BE.domain.notice.board.entity.Notice;
import com.modagbul.BE.domain.notice.board.repository.NoticeRepository;
import com.modagbul.BE.domain.notice.board.service.alarm.NoticeAlarmService;
import com.modagbul.BE.domain.notice.read.service.NoticeReadService;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeCreationServiceImpl implements NoticeCreationService {

    private final NoticeMapper noticeMapper;
    private final NoticeRepository noticeRepository;
    private final NoticeReadService noticeReadService;
    private final NoticeAlarmService noticeAlarmService;


    @Override
    public NoticeDto.CreateNoticeResponse createNotice(Long teamId, NoticeDto.CreateNoticeRequest createNoticeRequest) {
        //1. 공지사항 생성, 저장
        Notice notice = noticeMapper.toEntity(teamId, createNoticeRequest);
        noticeRepository.save(notice);
        //2. 공지사항-읽음 db 생성
        noticeReadService.createNoticeRead(teamId, notice);
        //3. 공지사항 fcm 알람
        noticeAlarmService.sendNewUploadNoticeAlarm(notice,teamId, SecurityUtils.getLoggedInUser().getUserId());
        return new NoticeDto.CreateNoticeResponse(notice.getNoticeId());
    }



}
