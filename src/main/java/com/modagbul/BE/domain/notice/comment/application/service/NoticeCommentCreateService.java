package com.modagbul.BE.domain.notice.comment.application.service;

import com.modagbul.BE.domain.notice.comment.application.dto.req.NoticeCommentRequest;
import com.modagbul.BE.domain.notice.comment.application.dto.res.NoticeCommentResponse;
import com.modagbul.BE.domain.notice.comment.application.dto.res.NoticeCommentResponse.CreateNoticeCommentResponse;
import com.modagbul.BE.domain.notice.comment.application.mapper.NoticeCommentMapper;
import com.modagbul.BE.domain.notice.comment.domain.entity.NoticeComment;
import com.modagbul.BE.domain.notice.comment.domain.service.NoticeCommentSaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeCommentCreateService {

    private final NoticeCommentMapper noticeCommentMapper;
    private final NoticeCommentSaveService noticeCommentSaveService;


    public CreateNoticeCommentResponse createNoticeComment(Long teamId, Long noticeId, NoticeCommentRequest.CreateNoticeCommentRequest createNoticeCommentRequest) {
        NoticeComment noticeComment=noticeCommentMapper.toEntity(teamId, noticeId, createNoticeCommentRequest);
        noticeCommentSaveService.saveNoticeComment(noticeComment);
        return new CreateNoticeCommentResponse(noticeComment.getNoticeCommentId());
    }

}
