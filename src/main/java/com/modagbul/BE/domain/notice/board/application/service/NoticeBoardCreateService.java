package com.modagbul.BE.domain.notice.board.application.service;

import com.modagbul.BE.domain.notice.board.application.dto.req.NoticeBoardRequest.CreateNoticeRequest;
import com.modagbul.BE.domain.notice.board.application.dto.res.NoticeBoardResponse.CreateNoticeResponse;
import com.modagbul.BE.domain.notice.board.application.mapper.NoticeBoardMapper;
import com.modagbul.BE.domain.notice.board.domain.entity.Notice;
import com.modagbul.BE.domain.notice.board.domain.service.NoticeBoardSaveService;
import com.modagbul.BE.domain.notice.read.application.service.NoticeReadCreateService;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeBoardCreateService {

    private final NoticeBoardMapper noticeMapper;
    private final NoticeBoardSaveService noticeBoardSaveService;
    private final NoticeReadCreateService noticeReadCreateService;
    private final NoticeBoardAlarmService noticeAlarmService;



    public CreateNoticeResponse createNotice(Long teamId, CreateNoticeRequest createNoticeRequest) {
        //1. 공지사항 생성, 저장
        Notice notice = noticeMapper.toEntity(teamId, createNoticeRequest);
        noticeBoardSaveService.saveNoticeBoard(notice);
        //2. 공지사항-읽음 db 생성
        noticeReadCreateService.createNoticeRead(teamId, notice);
        //3. 공지사항 fcm 알람
        noticeAlarmService.sendNewUploadNoticeAlarm(notice, teamId, SecurityUtils.getLoggedInUser().getUserId());
        return new CreateNoticeResponse(notice.getNoticeId());
    }



}
