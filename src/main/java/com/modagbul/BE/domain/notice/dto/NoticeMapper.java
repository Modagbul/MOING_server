package com.modagbul.BE.domain.notice.dto;

import com.modagbul.BE.domain.notice.dto.NoticeDto.CreateNoticeRequest;
import com.modagbul.BE.domain.notice.entity.Notice;
import org.springframework.stereotype.Component;

@Component
public class NoticeMapper {

    public Notice toEntity(CreateNoticeRequest createNoticeRequest){
        Notice notice=new Notice();
        notice.createNotice(createNoticeRequest.getTitle(),
                createNoticeRequest.getContent());
        return notice;
    }

}
