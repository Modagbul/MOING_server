package com.modagbul.BE.domain.notice.comment.application.service;

import com.modagbul.BE.domain.notice.board.application.service.NoticeBoardValidateService;
import com.modagbul.BE.domain.notice.board.domain.entity.Notice;
import com.modagbul.BE.domain.notice.comment.application.dto.res.NoticeCommentResponse;
import com.modagbul.BE.domain.notice.comment.application.dto.res.NoticeCommentResponse.GetNoticeCommentResponse;
import com.modagbul.BE.domain.notice.comment.application.mapper.NoticeCommentMapper;
import com.modagbul.BE.domain.notice.comment.domain.entity.NoticeComment;
import com.modagbul.BE.domain.notice.comment.domain.repsitory.NoticeCommentRepository;
import com.modagbul.BE.domain.notice.comment.domain.service.NoticeCommentQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeCommentReadService {

    private final NoticeBoardValidateService noticeBoardValidateService;
    private final NoticeCommentQueryService noticeCommentQueryService;
    private final NoticeCommentMapper noticeCommentMapper;

    public List<GetNoticeCommentResponse> getAllNoticeCommentByNoticeId(Long teamId, Long noticeId) {
        Notice notice=noticeBoardValidateService.validateNotice(teamId, noticeId);
        List<NoticeComment> noticeComments=noticeCommentQueryService.getNoticeCommentAllByNoticeId(noticeId);
        List<GetNoticeCommentResponse> result=new ArrayList<>();
        Map<Long, GetNoticeCommentResponse> map=new HashMap<>();
        noticeComments.stream().forEach(c->{
            GetNoticeCommentResponse getNoticeCommentResponse=noticeCommentMapper.toDto(c);
            map.put(getNoticeCommentResponse.getNoticeCommentId(), getNoticeCommentResponse);
            result.add(getNoticeCommentResponse);
        });
        return result;
    }
}
