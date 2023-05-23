package com.modagbul.BE.domain.vote.read.service;

import com.modagbul.BE.domain.team_member.entity.TeamMember;
import com.modagbul.BE.domain.team_member.repository.TeamMemberRepository;
import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.domain.vote.board.entity.Vote;
import com.modagbul.BE.domain.vote.board.exception.NotFoundVoteUserException;
import com.modagbul.BE.domain.vote.read.entity.VoteRead;
import com.modagbul.BE.domain.vote.read.repository.VoteReadRepository;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VoteReadServiceImpl implements VoteReadService{

    private final TeamMemberRepository teamMemberRepository;
    private final VoteReadRepository voteReadRepository;
    private final UserRepository userRepository;

    /**
     * 투표 읽음 정보 디비를 생성하는 메소드 : 생성한 사람은 무조건 읽음 처리
     *
     * @param teamId, vote
     */
    public void createVoteRead(Long teamId, Vote vote) {
        //읽음 정보 데이터 생성
        List<TeamMember> teamMembers = teamMemberRepository.findByTeamId(teamId);
        teamMembers.stream().forEach(teamMember -> {
            VoteRead voteRead = new VoteRead();
            voteRead.setVote(vote);
            voteRead.setUser(teamMember.getUser());
            voteReadRepository.save(voteRead);
        });
        //생성한 사람 읽음 처리
        updateVoteRead(vote);
        //투표 안읽은 사람 알림 가기 (전체 소모임원에서 생성한 사람 빼면 !) -> sendMultipleDevices() 함수 이용하면 될 듯
    }

    /**
     * 지금 현재 유저를 읽음 처리하는 메서드
     *
     * @param vote
     */

    public void updateVoteRead(Vote vote) {
        VoteRead voteRead = voteReadRepository.findByUserAndVote(userRepository.findById
                                (SecurityUtils.getLoggedInUser().getUserId()).orElseThrow(() -> new NotFoundEmailException()),
                        vote)
                .orElseThrow(() -> new NotFoundVoteUserException());
        voteRead.readVote();
    }
}
