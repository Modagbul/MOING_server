package com.modagbul.BE.domain.vote.read.application.service;

import com.modagbul.BE.domain.team_member.application.service.TeamMemberInfoService;
import com.modagbul.BE.domain.team_member.domain.entity.TeamMember;
import com.modagbul.BE.domain.team_member.domain.repository.TeamMemberRepository;
import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.domain.vote.board.domain.entity.Vote;
import com.modagbul.BE.domain.vote.board.exception.NotFoundVoteUserException;
import com.modagbul.BE.domain.vote.read.domain.entity.VoteRead;
import com.modagbul.BE.domain.vote.read.domain.repository.VoteReadRepository;
import com.modagbul.BE.domain.vote.read.domain.service.VoteReadSaveService;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VoteReadCreateService {

    private final TeamMemberInfoService teamMemberInfoService;
    private final VoteReadSaveService voteReadSaveService;
    private final VoteReadUpdateService voteReadUpdateService;

    /**
     * 투표 읽음 정보 디비를 생성하는 메소드 : 생성한 사람은 무조건 읽음 처리
     *
     * @param teamId, vote
     */
    public void createVoteRead(Long teamId, Vote vote) {
        //읽음 정보 데이터 생성
        List<TeamMember> teamMembers = teamMemberInfoService.getTeamMember(teamId);
        teamMembers.stream().forEach(teamMember -> {
            VoteRead voteRead = new VoteRead();
            voteRead.setVote(vote);
            voteRead.setUser(teamMember.getUser());
            voteReadSaveService.saveVoteRead(voteRead);
        });
        //생성한 사람 읽음 처리
        voteReadUpdateService.updateVoteRead(vote);
        //투표 안읽은 사람 알림 가기 (전체 소모임원에서 생성한 사람 빼면 !) -> sendMultipleDevices() 함수 이용하면 될 듯
    }
}
