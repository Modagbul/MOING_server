package com.modagbul.BE.domain.notice.service;

import com.modagbul.BE.domain.notice.dto.NoticeDto.CreateNoticeRequest;
import com.modagbul.BE.domain.notice.dto.NoticeDto.CreateNoticeResponse;
import com.modagbul.BE.domain.notice.dto.NoticeDto.GetNoticeDetailsResponse;
import com.modagbul.BE.domain.notice.dto.NoticeMapper;
import com.modagbul.BE.domain.notice.entity.Notice;
import com.modagbul.BE.domain.notice.exception.NotFoundNoticeIdException;
import com.modagbul.BE.domain.notice.exception.NotFoundNoticeUserException;
import com.modagbul.BE.domain.notice.repository.NoticeRepository;
import com.modagbul.BE.domain.notice_read.entity.NoticeRead;
import com.modagbul.BE.domain.notice_read.repository.NoticeReadRepository;
import com.modagbul.BE.domain.team_member.entity.TeamMember;
import com.modagbul.BE.domain.team_member.repository.TeamMemberRepository;
import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class NoticeServiceImpl implements NoticeService{

    private final NoticeMapper noticeMapper;
    private final NoticeRepository noticeRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final NoticeReadRepository noticeReadRepository;
    private final UserRepository userRepository;

    @Override
    public CreateNoticeResponse createNotice(Long teamId, CreateNoticeRequest createNoticeRequest) {
        //1. 공지사항 생성, 저장
        Notice notice = noticeMapper.toEntity(teamId, createNoticeRequest);
        noticeRepository.save(notice);
        //2. 공지사항-읽음 db 생성
        createNoticeRead(teamId, notice);
        return new CreateNoticeResponse(notice.getNoticeId());
    }

    @Override
    public void deleteNotice(Long noticeId) {
        Notice notice=validateNotice(noticeId);
        notice.deleteNotice();
    }

    @Override
    public Notice validateNotice(Long noticeId){
        return this.noticeRepository.findNotDeletedByNoticeId(noticeId).orElseThrow(()->new NotFoundNoticeIdException());
    }

    @Override
    public GetNoticeDetailsResponse getNoticeDetails(Long noticeId) {
        //1. 공지사항 유효성 체크
        Notice notice=this.validateNotice(noticeId);
        //2. 사용자 읽음 처리
        updateNoticeRead(notice);
        //3. 공지 조회 -> 이때 읽은 사용자는 안 뜨게 해야 함
        return noticeMapper.toDto(notice,noticeReadRepository.getNotReadUsersNickName(noticeId));
    }

    /**
     * 읽음 정보 디비를 생성하는 메소드 : 생성한 사람은 무조건 읽음 처리
     * @param teamId, notice
     */
    private void createNoticeRead(Long teamId, Notice notice){
        //읽음 정보 데이터 생성
        List<TeamMember> teamMembers = teamMemberRepository.findByTeamId(teamId);
        teamMembers.stream().forEach(teamMember -> {
            NoticeRead noticeRead = new NoticeRead();
            noticeRead.setNotice(notice);
            noticeRead.setUser(teamMember.getUser());
            noticeReadRepository.save(noticeRead);
        });
        //생성한 사람 읽음 처리
        updateNoticeRead(notice);
    }

    private void updateNoticeRead(Notice notice){
        NoticeRead noticeRead=noticeReadRepository.findByUserAndNotice(userRepository.findById
                                (SecurityUtils.getLoggedInUser().getUserId()).orElseThrow(()->new NotFoundEmailException()),
                        notice)
                .orElseThrow(()-> new NotFoundNoticeUserException());
        noticeRead.readNotice();
    }
}
