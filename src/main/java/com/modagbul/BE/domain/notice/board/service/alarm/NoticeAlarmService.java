package com.modagbul.BE.domain.notice.board.service.alarm;

import com.modagbul.BE.domain.notice.board.entity.Notice;

public interface NoticeAlarmService {
    void sendNewUploadNoticeAlarm(Notice notice, Long teamId, Long userId); //noticealarm
}
