package com.modagbul.BE.domain.notice.read.repository;

import java.util.List;

public interface NoticeReadRepositoryCustom{
    List<String> getNotReadUsersNickName(Long noticeId);
}
