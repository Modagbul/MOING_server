package com.modagbul.BE.domain.notice.comment.service;

import com.modagbul.BE.domain.notice.comment.dto.NoticeCommentDto.CreateNoticeCommentRequest;
import com.modagbul.BE.domain.notice.comment.dto.NoticeCommentDto.CreateNoticeCommentResponse;
import com.modagbul.BE.domain.notice.comment.dto.NoticeCommentDto.GetNoticeCommentResponse;
import com.modagbul.BE.domain.notice.comment.entity.NoticeComment;

import java.util.List;

public interface NoticeCommentService {
    CreateNoticeCommentResponse createNoticeComment(Long noticeId, CreateNoticeCommentRequest createNoticeCommentRequest);
    void deleteNoticeComment(Long noticeCommentId);
    NoticeComment validateNoticeComment(Long noticeCommentId);
    List<GetNoticeCommentResponse> getAllNoticeCommentByNoticeId(Long noticeId);
}
