package com.modagbul.BE.domain.notice.board.service.validate;

import com.modagbul.BE.domain.notice.board.entity.Notice;
import com.modagbul.BE.domain.user.entity.User;

public interface NoticeValidationService {
    Notice validateNotice(Long teamId, Long noticeId);
    void validateWriter(User user, Notice notice); //validate
}
