package com.modagbul.BE.domain.notice.board.application.service;

import com.modagbul.BE.domain.notice.board.application.dto.res.NoticeBoardResponse;
import com.modagbul.BE.domain.notice.board.application.mapper.NoticeBoardMapper;
import com.modagbul.BE.domain.notice.board.domain.entity.Notice;
import com.modagbul.BE.domain.notice.board.domain.service.NoticeBoardQueryService;
import com.modagbul.BE.domain.notice.read.application.service.NoticeReadUpdateService;
import com.modagbul.BE.domain.notice.read.application.service.NoticeReadUserNameService;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeBoardReadService {

    private final NoticeBoardMapper noticeMapper;
    private final NoticeBoardQueryService noticeBoardQueryService;
    private final NoticeReadUpdateService noticeReadUpdateService;
    private final NoticeBoardValidateService noticeBoardValidateService;
    private final NoticeReadUserNameService noticeReadUserNameService;


    public NoticeBoardResponse.GetNoticeDetailsResponse getNoticeDetails(Long teamId, Long noticeId) {
        //1. 공지사항 유효성 체크
        Notice notice = noticeBoardValidateService.validateNotice(teamId, noticeId);
        //2. 사용자 읽음 처리
        noticeReadUpdateService.updateNoticeRead(notice);
        //3. 공지 조회 -> 이때 읽은 사용자는 안 뜨게 해야 함
        return noticeMapper.toDto(notice, noticeReadUserNameService.getUnReadUserNickName(noticeId));
    }


    public NoticeBoardResponse.GetNoticeAllResponse getNoticeAll(Long teamId) {
        return noticeBoardQueryService.getNoticeAllByTeamId(teamId, SecurityUtils.getLoggedInUser().getUserId());
    }


    public List<NoticeBoardResponse.GetUnReadNoticeResponse> getUnReadNotice(Long teamId) {
        return noticeBoardQueryService.getUnReadNoticeByTeamId(teamId, SecurityUtils.getLoggedInUser().getUserId());
    }
}
