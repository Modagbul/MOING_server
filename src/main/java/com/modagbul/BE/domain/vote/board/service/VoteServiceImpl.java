package com.modagbul.BE.domain.vote.board.service;

import com.modagbul.BE.domain.notice.board.exception.NotFoundNoticeUserException;
import com.modagbul.BE.domain.notice.board.dto.NoticeDto;
import com.modagbul.BE.domain.team_member.entity.TeamMember;
import com.modagbul.BE.domain.team_member.repository.TeamMemberRepository;
import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.domain.vote.board.dto.VoteDto;
import com.modagbul.BE.domain.vote.board.dto.VoteDto.CreateVoteRequest;
import com.modagbul.BE.domain.vote.board.dto.VoteDto.CreateVoteResponse;
import com.modagbul.BE.domain.vote.board.dto.VoteMapper;
import com.modagbul.BE.domain.vote.board.entity.Vote;
import com.modagbul.BE.domain.vote.board.exception.NotFoundVoteUserException;
import com.modagbul.BE.domain.vote.board.repository.VoteRepository;
import com.modagbul.BE.domain.vote.content.entity.VoteContent;
import com.modagbul.BE.domain.vote.content.repository.VoteContentRepository;
import com.modagbul.BE.domain.vote.read.entity.VoteRead;
import com.modagbul.BE.domain.vote.read.repository.VoteReadRepository;
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
public class VoteServiceImpl implements VoteService{

    private final VoteRepository voteRepository;
    private final VoteMapper voteMapper;
    private final VoteContentRepository voteContentRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final VoteReadRepository voteReadRepository;
    private final UserRepository userRepository;

    @Override
    public CreateVoteResponse createVote(Long teamId, CreateVoteRequest createVoteRequest) {
        //1. 투표  생성, 저장
        Vote vote=voteMapper.toEntity(teamId, createVoteRequest);
        voteRepository.save(vote);

        //2. 투표 선택지 저장
        createVoteContent(createVoteRequest, vote);

        //3. 투표-읽음 db 생성
        createVoteRead(teamId, vote);
        System.out.println("3");
        return new CreateVoteResponse(vote.getVoteId());
    }

    /**
     * 투표 선택지를 저장하는 메서드
     * @param createVoteRequest
     */
    private void createVoteContent(CreateVoteRequest createVoteRequest, Vote vote){
        List<String> contents=createVoteRequest.getChoices();
        System.out.println(contents.toString());
        contents.stream().forEach(content->{
            VoteContent voteContent=new VoteContent();
            voteContent.setContent(content);
            voteContent.setVote(vote);
            voteContentRepository.save(voteContent);
        });
    }

    /**
     * 투표 읽음 정보 디비를 생성하는 메소드 : 생성한 사람은 무조건 읽음 처리
     * @param teamId, notice
     */
    private void createVoteRead(Long teamId, Vote vote){
        //읽음 정보 데이터 생성
        List<TeamMember> teamMembers = teamMemberRepository.findByTeamId(teamId);
        teamMembers.stream().forEach(teamMember -> {
            VoteRead voteRead=new VoteRead();
            voteRead.setVote(vote);
            voteRead.setUser(teamMember.getUser());
            voteReadRepository.save(voteRead);
        });
        //생성한 사람 읽음 처리
        updateVoteRead(vote);
        //공지사항 안읽은 사람 알림 가기 (전체 소모임원에서 생성한 사람 빼면 !) -> sendMultipleDevices() 함수 이용하면 될 듯
    }

    private void updateVoteRead(Vote vote){
        VoteRead voteRead=voteReadRepository.findByUserAndVote(userRepository.findById
                                (SecurityUtils.getLoggedInUser().getUserId()).orElseThrow(()->new NotFoundEmailException()),
                        vote)
                .orElseThrow(()-> new NotFoundVoteUserException());
        voteRead.readVote();
    }
}
