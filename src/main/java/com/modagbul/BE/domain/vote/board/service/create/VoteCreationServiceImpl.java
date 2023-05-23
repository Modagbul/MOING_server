package com.modagbul.BE.domain.vote.board.service.create;

import com.modagbul.BE.domain.vote.board.dto.VoteDto;
import com.modagbul.BE.domain.vote.board.dto.VoteMapper;
import com.modagbul.BE.domain.vote.board.entity.Vote;
import com.modagbul.BE.domain.vote.board.repository.VoteRepository;
import com.modagbul.BE.domain.vote.board.service.alarm.VoteAlarmService;
import com.modagbul.BE.domain.vote.content.service.VoteContentService;
import com.modagbul.BE.domain.vote.read.service.VoteReadService;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteCreationServiceImpl implements VoteCreationService {

    private final VoteMapper voteMapper;
    private final VoteRepository voteRepository;
    private final VoteContentService voteContentService;
    private final VoteReadService voteReadService;
    private final VoteAlarmService voteAlarmService;


    @Override
    public VoteDto.CreateVoteResponse createVote(Long teamId, VoteDto.CreateVoteRequest createVoteRequest) {
        //1. 투표  생성, 저장
        Vote vote = voteMapper.toEntity(teamId, createVoteRequest);
        voteRepository.save(vote);

        //2. 투표 선택지 저장
        voteContentService.createVoteContent(createVoteRequest, vote);

        //3. 투표-읽음 db 생성
        voteReadService.createVoteRead(teamId, vote);

        //4. 투표 fcm 알림
        voteAlarmService.sendNewUploadVoteAlarm(vote, teamId, SecurityUtils.getLoggedInUser().getUserId());
        return new VoteDto.CreateVoteResponse(vote.getVoteId());
    }
}
