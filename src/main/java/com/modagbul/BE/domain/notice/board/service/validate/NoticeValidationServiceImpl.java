package com.modagbul.BE.domain.notice.board.service.validate;

import com.modagbul.BE.domain.notice.board.entity.Notice;
import com.modagbul.BE.domain.notice.board.exception.NotFoundNoticeIdException;
import com.modagbul.BE.domain.notice.board.exception.NotNoticeWriterException;
import com.modagbul.BE.domain.notice.board.repository.NoticeRepository;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.team.service.validate.TeamValidationService;
import com.modagbul.BE.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeValidationServiceImpl implements NoticeValidationService{

    private final TeamValidationService teamValidationService;
    private final NoticeRepository noticeRepository;

    /**
     * 공지가 삭제되었는지 확인하는 메서드: 유효성 체크
     * @param noticeId
     * @return 공지가 삭제되지 않았다면 Notice 반환
     */
    @Override
    public Notice validateNotice(Long teamId, Long noticeId){
        Team team=teamValidationService.validateTeam(teamId);
        return this.noticeRepository.findNotDeletedByNoticeId(noticeId).orElseThrow(()->new NotFoundNoticeIdException());
    }

    /**
     * 공지를 작성한 유저인지 확인하는 메서드
     * @param user
     * @param notice
     */

    @Override
    public void validateWriter(User user, Notice notice){
        if(notice.getUser().getUserId()!=user.getUserId())
            throw new NotNoticeWriterException();
    }
}
