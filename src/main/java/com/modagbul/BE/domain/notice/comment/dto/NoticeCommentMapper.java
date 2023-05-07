package com.modagbul.BE.domain.notice.comment.dto;

import com.modagbul.BE.domain.notice.board.entity.Notice;
import com.modagbul.BE.domain.notice.board.service.NoticeService;
import com.modagbul.BE.domain.notice.comment.dto.NoticeCommentDto.GetNoticeCommentResponse;
import com.modagbul.BE.domain.notice.comment.entity.NoticeComment;
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
    public NoticeComment toEntity(Long noticeId, NoticeCommentDto.CreateNoticeCommentRequest createNoticeCommentRequest){
        Notice notice=noticeService.validateNotice(noticeId);
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
