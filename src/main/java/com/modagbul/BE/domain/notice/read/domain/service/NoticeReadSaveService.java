package com.modagbul.BE.domain.notice.read.domain.service;

import com.modagbul.BE.domain.notice.read.domain.entity.NoticeRead;
import com.modagbul.BE.domain.notice.read.domain.repository.NoticeReadRepository;
import com.modagbul.BE.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@DomainService
@Transactional
@RequiredArgsConstructor
public class NoticeReadSaveService {
    private final NoticeReadRepository noticeReadRepository;

    public void saveNoticeRead(NoticeRead noticeRead){
        noticeReadRepository.save(noticeRead);
    }
}
