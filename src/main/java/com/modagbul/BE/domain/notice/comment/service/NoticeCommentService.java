package com.modagbul.BE.domain.notice.comment.service;

import com.modagbul.BE.domain.notice.comment.dto.NoticeCommentDto.CreateNoticeCommentRequest;
import com.modagbul.BE.domain.notice.comment.dto.NoticeCommentDto.CreateNoticeCommentResponse;
import com.modagbul.BE.domain.notice.comment.dto.NoticeCommentDto.GetNoticeCommentResponse;
import com.modagbul.BE.domain.notice.comment.entity.NoticeComment;

import java.util.List;

public interface NoticeCommentService {
    CreateNoticeCommentResponse createNoticeComment(Long teamId, Long noticeId, CreateNoticeCommentRequest createNoticeCommentRequest);
    void deleteNoticeComment(Long teamId, Long noticeId, Long noticeCommentId);
    NoticeComment validateNoticeComment(Long teamId, Long noticeId, Long noticeCommentId);
    List<GetNoticeCommentResponse> getAllNoticeCommentByNoticeId(Long teamId ,Long noticeId);
}
