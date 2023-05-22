package com.modagbul.BE.domain.notice.read.service;

import com.modagbul.BE.domain.notice.board.entity.Notice;
import com.modagbul.BE.domain.notice.board.exception.NotFoundNoticeUserException;
import com.modagbul.BE.domain.notice.read.entity.NoticeRead;
import com.modagbul.BE.domain.notice.read.repository.NoticeReadRepository;
import com.modagbul.BE.domain.team_member.entity.TeamMember;
import com.modagbul.BE.domain.team_member.repository.TeamMemberRepository;
import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeReadServiceImpl implements NoticeReadService{

    private final TeamMemberRepository teamMemberRepository;
    private final NoticeReadRepository noticeReadRepository;
    private final UserRepository userRepository;


    /**
     * 읽음 정보 디비를 생성하는 메소드 : 생성한 사람은 무조건 읽음 처리
     * @param teamId, notice
     */
    @Override
    public void createNoticeRead(Long teamId, Notice notice){
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
        //공지사항 안읽은 사람 알림 가기 (전체 소모임원에서 생성한 사람 빼면 !) -> sendMultipleDevices() 함수 이용하면 될 듯

    }

    /**
     * 지금 현재 유저를 읽음 처리하는 메서드
     * @param notice
     */
    @Override
    public void updateNoticeRead(Notice notice){
        NoticeRead noticeRead=noticeReadRepository.findByUserAndNotice(userRepository.findById
                                (SecurityUtils.getLoggedInUser().getUserId()).orElseThrow(()->new NotFoundEmailException()),
                        notice)
                .orElseThrow(()-> new NotFoundNoticeUserException());
        noticeRead.readNotice();
    }

    @Override
    public List<String> getNotReadUsersNickName(Long noticeId){
        return noticeReadRepository.getNotReadUsersNickName(noticeId);
    }
}
