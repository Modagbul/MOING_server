package com.modagbul.BE.domain.notice.read.service;

import com.modagbul.BE.domain.notice.board.dto.NoticeDto;
import com.modagbul.BE.domain.notice.board.entity.Notice;

import java.util.List;

public interface NoticeReadService {

    void createNoticeRead(Long teamId, Notice notice); //noticeread
    void updateNoticeRead(Notice notice); //noticeread
    List<String> getNotReadUsersNickName(Long noticeId);

}
