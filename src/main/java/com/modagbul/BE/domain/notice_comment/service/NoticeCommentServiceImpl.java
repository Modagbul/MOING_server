package com.modagbul.BE.domain.notice_comment.service;

import com.modagbul.BE.domain.notice.service.NoticeService;
import com.modagbul.BE.domain.notice_comment.dto.NoticeCommentDto.CreateNoticeCommentRequest;
import com.modagbul.BE.domain.notice_comment.dto.NoticeCommentDto.CreateNoticeCommentResponse;
import com.modagbul.BE.domain.notice_comment.dto.NoticeCommentDto.GetNoticeCommentResponse;
import com.modagbul.BE.domain.notice_comment.dto.NoticeCommentMapper;
import com.modagbul.BE.domain.notice_comment.entity.NoticeComment;
import com.modagbul.BE.domain.notice_comment.exception.NotFoundNoticeCommentIdException;
import com.modagbul.BE.domain.notice_comment.repsitory.NoticeCommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class NoticeCommentServiceImpl implements NoticeCommentService{

    private final NoticeCommentRepository noticeCommentRepository;
    private final NoticeCommentMapper noticeCommentMapper;

    @Override
    public CreateNoticeCommentResponse createNoticeComment(Long noticeId, CreateNoticeCommentRequest createNoticeCommentRequest) {
        NoticeComment noticeComment=noticeCommentMapper.toEntity(noticeId, createNoticeCommentRequest);
        noticeCommentRepository.save(noticeComment);
        return new CreateNoticeCommentResponse(noticeComment.getNoticeCommentId());
    }

    @Override
    public void deleteNoticeComment(Long noticeCommentId) {
        NoticeComment noticeComment=validateNoticeComment(noticeCommentId);
        noticeComment.deleteNoticeComment();
    }

    @Override
    public NoticeComment validateNoticeComment(Long noticeCommentId) {
        return this.noticeCommentRepository.findNotDeletedByCommentId(noticeCommentId).orElseThrow(()->new NotFoundNoticeCommentIdException());
    }

    @Override
    public List<GetNoticeCommentResponse> getAllNoticeCommentByNoticeId(Long noticeId) {
        List<NoticeComment> noticeComments=noticeCommentRepository.findAllCommentsByNoticeId(noticeId);
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
