package com.modagbul.BE.domain.notice_read.repository;

import com.modagbul.BE.domain.notice_read.entity.NoticeRead;

import java.util.List;

public interface NoticeReadRepositoryCustom{
    List<String> getNotReadUsersNickName(Long noticeId);
}
