package com.modagbul.BE.domain.notice.board.application.service;

import com.modagbul.BE.domain.notice.board.domain.entity.Notice;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeBoardDeleteService {

    private final NoticeBoardValidateService noticeBoardValidateService;



    public void deleteNotice(Long teamId, Long noticeId) {
        Notice notice = noticeBoardValidateService.validateNotice(teamId, noticeId);
        noticeBoardValidateService.validateWriter(SecurityUtils.getLoggedInUser(), notice);
        notice.deleteNotice();
    }

}
