package com.modagbul.BE.domain.notice.read.application.service;

import com.modagbul.BE.domain.notice.read.domain.service.NoticeReadQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeReadUserNameService {
    private final NoticeReadQueryService noticeReadQueryService;

    public List<String> getUnReadUserNickName(Long noticeId){
        return noticeReadQueryService.getUnReadUserNickNameByNoticeId(noticeId);
    }

}
