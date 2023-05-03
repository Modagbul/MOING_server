package com.modagbul.BE.domain.notice_comment.service;

import com.modagbul.BE.domain.notice_comment.dto.NoticeCommentDto.CreateNoticeCommentRequest;
import com.modagbul.BE.domain.notice_comment.dto.NoticeCommentDto.CreateNoticeCommentResponse;
import com.modagbul.BE.domain.notice_comment.dto.NoticeCommentDto.GetNoticeCommentResponse;
import com.modagbul.BE.domain.notice_comment.entity.NoticeComment;

import java.util.List;

public interface NoticeCommentService {
    CreateNoticeCommentResponse createNoticeComment(Long noticeId, CreateNoticeCommentRequest createNoticeCommentRequest);
    void deleteNoticeComment(Long noticeCommentId);
    NoticeComment validateNoticeComment(Long noticeCommentId);
    List<GetNoticeCommentResponse> getAllNoticeCommentByNoticeId(Long noticeId);
}
