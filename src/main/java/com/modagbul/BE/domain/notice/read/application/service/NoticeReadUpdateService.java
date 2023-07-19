package com.modagbul.BE.domain.notice.read.application.service;

import com.modagbul.BE.domain.notice.board.domain.entity.Notice;
import com.modagbul.BE.domain.notice.read.domain.entity.NoticeRead;
import com.modagbul.BE.domain.notice.read.domain.service.NoticeReadQueryService;
import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeReadUpdateService {
    private final NoticeReadQueryService noticeReadQueryService;
    private final UserRepository userRepository;
    public void updateNoticeRead(Notice notice){
        NoticeRead noticeRead=noticeReadQueryService.getNoticeReadByUserAndNotice(userRepository.findById
                (SecurityUtils.getLoggedInUser().getUserId()).orElseThrow(()->new NotFoundEmailException()),notice);
        noticeRead.readNotice();
    }

}
