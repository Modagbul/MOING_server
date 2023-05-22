package com.modagbul.BE.domain.notice.board.service.info;

import com.modagbul.BE.domain.notice.board.dto.NoticeDto;
import com.modagbul.BE.domain.notice.board.dto.NoticeMapper;
import com.modagbul.BE.domain.notice.board.entity.Notice;
import com.modagbul.BE.domain.notice.board.repository.NoticeRepository;
import com.modagbul.BE.domain.notice.board.service.validate.NoticeValidationService;
import com.modagbul.BE.domain.notice.read.service.NoticeReadService;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeInfoServiceImpl implements NoticeInfoService{

    private final NoticeMapper noticeMapper;
    private final NoticeRepository noticeRepository;
    private final NoticeReadService noticeReadService;
    private final NoticeValidationService noticeValidationService;

    @Override
    public NoticeDto.GetNoticeDetailsResponse getNoticeDetails(Long teamId, Long noticeId) {
        //1. 공지사항 유효성 체크
        Notice notice=noticeValidationService.validateNotice(teamId, noticeId);
        //2. 사용자 읽음 처리
        noticeReadService.updateNoticeRead(notice);
        //3. 공지 조회 -> 이때 읽은 사용자는 안 뜨게 해야 함
        return noticeMapper.toDto(notice,noticeReadService.getNotReadUsersNickName(noticeId));
    }

    @Override
    public NoticeDto.GetNoticeAllResponse getNoticeAll(Long teamId) {
        return noticeRepository.getNoticeAllByTeamId(teamId, SecurityUtils.getLoggedInUser().getUserId());
    }

    @Override
    public List<NoticeDto.GetUnReadNoticeResponse> getUnReadNotice(Long teamId) {
        return noticeRepository.getUnReadNoticeByTeamId(teamId, SecurityUtils.getLoggedInUser().getUserId());
    }
}
