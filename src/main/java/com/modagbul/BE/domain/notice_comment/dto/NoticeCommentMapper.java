package com.modagbul.BE.domain.notice_comment.dto;

import com.modagbul.BE.domain.notice.entity.Notice;
import com.modagbul.BE.domain.notice.exception.NotFoundNoticeIdException;
import com.modagbul.BE.domain.notice.repository.NoticeRepository;
import com.modagbul.BE.domain.notice.service.NoticeService;
import com.modagbul.BE.domain.notice_comment.dto.NoticeCommentDto.CreateNoticeCommentRequest;
import com.modagbul.BE.domain.notice_comment.dto.NoticeCommentDto.GetNoticeCommentResponse;
import com.modagbul.BE.domain.notice_comment.entity.NoticeComment;
import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NoticeCommentMapper {
    private final NoticeService noticeService;

    private final UserRepository userRepository;
    public NoticeComment toEntity(CreateNoticeCommentRequest createNoticeCommentRequest){
        Notice notice=noticeService.validateNotice(createNoticeCommentRequest.getNoticeId());
        NoticeComment noticeComment=new NoticeComment();

        noticeComment.createNoticeComment(createNoticeCommentRequest.getContent());
        noticeComment.setNotice(notice);
        noticeComment.setUser(userRepository.findById(SecurityUtils.getLoggedInUser().getUserId()).orElseThrow(()->new NotFoundEmailException()));
        return noticeComment;
    }

    public GetNoticeCommentResponse toDto(NoticeComment noticeComment){
        return new GetNoticeCommentResponse(noticeComment.getNoticeCommentId(),noticeComment.getContent(), noticeComment.getUser().getUserId(), noticeComment.getUser().getNickName(), noticeComment.getUser().getImageUrl(), noticeComment.getCreatedDate());
    }
}
