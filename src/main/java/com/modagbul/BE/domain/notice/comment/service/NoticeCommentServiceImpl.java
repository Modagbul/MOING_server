package com.modagbul.BE.domain.notice.comment.service;

import com.modagbul.BE.domain.notice.comment.dto.NoticeCommentDto.CreateNoticeCommentRequest;
import com.modagbul.BE.domain.notice.comment.dto.NoticeCommentDto.CreateNoticeCommentResponse;
import com.modagbul.BE.domain.notice.comment.dto.NoticeCommentDto.GetNoticeCommentResponse;
import com.modagbul.BE.domain.notice.comment.dto.NoticeCommentMapper;
import com.modagbul.BE.domain.notice.comment.entity.NoticeComment;
import com.modagbul.BE.domain.notice.comment.exception.NotFoundNoticeCommentIdException;
import com.modagbul.BE.domain.notice.comment.exception.NotNoticeCommentWriterException;
import com.modagbul.BE.domain.notice.comment.repsitory.NoticeCommentRepository;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
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
        validateUser(SecurityUtils.getLoggedInUser(),noticeComment);
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

    /**
     * 댓글을 작성한 유저인지 확인하는 메서드
     * @param user
     * @param noticeComment
     */
    private void validateUser(User user, NoticeComment noticeComment){
        if(noticeComment.getUser()!=user)
            throw new NotNoticeCommentWriterException();
    }


}
