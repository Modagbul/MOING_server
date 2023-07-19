package com.modagbul.BE.domain.notice.read.domain.repository;

import java.util.List;

public interface NoticeReadRepositoryCustom{
    List<String> findUnReadUserNickNameByNoticeId(Long noticeId);
}
