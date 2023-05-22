package com.modagbul.BE.domain.notice.board.service.manage;

import com.modagbul.BE.domain.notice.board.entity.Notice;
import com.modagbul.BE.domain.notice.board.service.validate.NoticeValidationService;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeManagementServiceImpl implements NoticeManagementService{

    private final NoticeValidationService noticeValidationService;

    @Override
    public void deleteNotice(Long teamId, Long noticeId) {
        Notice notice=noticeValidationService.validateNotice(teamId, noticeId);
        noticeValidationService.validateWriter(SecurityUtils.getLoggedInUser(),notice);
        notice.deleteNotice();
    }

}
