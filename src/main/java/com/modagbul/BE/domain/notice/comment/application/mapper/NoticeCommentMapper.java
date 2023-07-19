package com.modagbul.BE.domain.notice.comment.application.mapper;

import com.modagbul.BE.domain.notice.board.application.service.NoticeBoardValidateService;
import com.modagbul.BE.domain.notice.board.domain.entity.Notice;
import com.modagbul.BE.domain.notice.comment.application.dto.req.NoticeCommentRequest;
import com.modagbul.BE.domain.notice.comment.application.dto.res.NoticeCommentResponse.GetNoticeCommentResponse;
import com.modagbul.BE.domain.notice.comment.domain.entity.NoticeComment;
import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class NoticeCommentMapper {
    private final NoticeBoardValidateService noticeBoardValidateService;

    private final UserRepository userRepository;

    public NoticeComment toEntity(Long teamId, Long noticeId, NoticeCommentRequest.CreateNoticeCommentRequest createNoticeCommentRequest) {
        Notice notice = noticeBoardValidateService.validateNotice(teamId, noticeId);
        NoticeComment noticeComment = new NoticeComment();

        noticeComment.createNoticeComment(createNoticeCommentRequest.getContent());
        noticeComment.setNotice(notice);
        noticeComment.setUser(userRepository.findById(SecurityUtils.getLoggedInUser().getUserId()).orElseThrow(() -> new NotFoundEmailException()));
        return noticeComment;
    }

    public GetNoticeCommentResponse toDto(NoticeComment noticeComment) {
        return new GetNoticeCommentResponse(noticeComment.getNoticeCommentId(), noticeComment.getContent(), noticeComment.getUser().getUserId(), noticeComment.getUser().getNickName(), noticeComment.getUser().getImageUrl(), noticeComment.getCreatedDate());
    }
}
