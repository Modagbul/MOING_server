package com.modagbul.BE.domain.vote.board.application.service;

import com.modagbul.BE.domain.vote.board.application.dto.req.VoteRequest;
import com.modagbul.BE.domain.vote.board.application.dto.req.VoteRequest.CreateVoteRequest;
import com.modagbul.BE.domain.vote.board.application.dto.res.VoteResponse;
import com.modagbul.BE.domain.vote.board.application.dto.res.VoteResponse.CreateVoteResponse;
import com.modagbul.BE.domain.vote.board.application.mapper.VoteMapper;
import com.modagbul.BE.domain.vote.board.domain.entity.Vote;
import com.modagbul.BE.domain.vote.board.domain.service.VoteBoardSaveService;
import com.modagbul.BE.domain.vote.content.application.service.VoteContentCreateService;
import com.modagbul.BE.domain.vote.read.application.service.VoteReadCreateService;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteBoardCreateService {
    private final VoteBoardSaveService voteBoardSaveService;
    private final VoteMapper voteMapper;
    private final VoteBoardAlarmService voteBoardAlarmService;
    private final VoteContentCreateService voteContentCreateService;
    private final VoteReadCreateService voteReadCreateService;


    public CreateVoteResponse createVote(Long teamId, CreateVoteRequest createVoteRequest) {
        //1. 투표  생성, 저장
        Vote vote = voteMapper.toEntity(teamId, createVoteRequest);
        voteBoardSaveService.saveVote(vote);

        //2. 투표 선택지 저장
        voteContentCreateService.createVoteContent(createVoteRequest, vote);

        //3. 투표-읽음 db 생성
        voteReadCreateService.createVoteRead(teamId, vote);

        //4. 투표 fcm 알림
        voteBoardAlarmService.sendNewUploadVoteAlarm(vote, teamId, SecurityUtils.getLoggedInUser().getUserId());
        return new CreateVoteResponse(vote.getVoteId());
    }
}
