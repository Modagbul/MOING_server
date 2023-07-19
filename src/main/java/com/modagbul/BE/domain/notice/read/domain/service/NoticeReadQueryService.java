package com.modagbul.BE.domain.notice.read.domain.service;

import com.modagbul.BE.domain.notice.board.domain.entity.Notice;
import com.modagbul.BE.domain.notice.board.exception.NotFoundNoticeUserException;
import com.modagbul.BE.domain.notice.read.domain.entity.NoticeRead;
import com.modagbul.BE.domain.notice.read.domain.repository.NoticeReadRepository;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

@DomainService
@Transactional
@RequiredArgsConstructor
public class NoticeReadQueryService {
    private final NoticeReadRepository noticeReadRepository;
    private final UserRepository userRepository;
    public List<String> getUnReadUserNickNameByNoticeId(Long noticeId){
        return noticeReadRepository.findUnReadUserNickNameByNoticeId(noticeId);
    }

    public NoticeRead getNoticeReadByUserAndNotice(User user, Notice notice) {
        return noticeReadRepository.findByUserAndNotice(user,notice)
                .orElseThrow(() -> new NotFoundNoticeUserException());
    }
}
