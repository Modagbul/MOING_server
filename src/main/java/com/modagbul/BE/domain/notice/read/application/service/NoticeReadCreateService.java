package com.modagbul.BE.domain.notice.read.application.service;

import com.modagbul.BE.domain.notice.board.domain.entity.Notice;
import com.modagbul.BE.domain.notice.read.domain.entity.NoticeRead;
import com.modagbul.BE.domain.notice.read.domain.service.NoticeReadSaveService;
import com.modagbul.BE.domain.team_member.domain.entity.TeamMember;
import com.modagbul.BE.domain.team_member.domain.repository.TeamMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class NoticeReadCreateService {

    private final TeamMemberRepository teamMemberRepository;
    private final NoticeReadSaveService noticeReadSaveService;
    private final NoticeReadUpdateService noticeReadUpdateService;

    public void createNoticeRead(Long teamId, Notice notice){
        //읽음 정보 데이터 생성
        List<TeamMember> teamMembers = teamMemberRepository.findByTeamId(teamId);
        teamMembers.stream().forEach(teamMember -> {
            NoticeRead noticeRead = new NoticeRead();
            noticeRead.setNotice(notice);
            noticeRead.setUser(teamMember.getUser());
            noticeReadSaveService.saveNoticeRead(noticeRead);
        });
        //생성한 사람 읽음 처리
        noticeReadUpdateService.updateNoticeRead(notice);
        //공지사항 안읽은 사람 알림 가기 (전체 소모임원에서 생성한 사람 빼면 !) -> sendMultipleDevices() 함수 이용하면 될 듯

    }
}
