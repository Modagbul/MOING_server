package com.modagbul.BE.domain.vote.board.application.service;

import com.modagbul.BE.domain.vote.board.application.dto.req.VoteRequest;
import com.modagbul.BE.domain.vote.board.application.dto.req.VoteRequest.DoVoteRequest;
import com.modagbul.BE.domain.vote.board.application.dto.res.VoteResponse;
import com.modagbul.BE.domain.vote.board.application.dto.res.VoteResponse.GetVoteDetailsResponse;
import com.modagbul.BE.domain.vote.board.domain.entity.Vote;
import com.modagbul.BE.domain.vote.board.domain.service.VoteBoardQueryService;
import com.modagbul.BE.domain.vote.content.application.service.VoteContentUpdateService;
import com.modagbul.BE.domain.vote.read.application.service.VoteReadUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteBoardDoService {
    private final VoteValidateService voteValidateService;
    private final VoteBoardQueryService voteBoardQueryService;
    private final VoteContentUpdateService voteContentUpdateService;
    private final VoteReadUpdateService voteReadUpdateService;

    public GetVoteDetailsResponse doVote(Long teamId, Long voteId, DoVoteRequest doVoteRequest) {
        //1. 유효성 체크
        Vote vote = voteValidateService.validateVote(teamId, voteId);
        //2. 투표 선택지 업데이트
        voteContentUpdateService.updateVoteContent(doVoteRequest, vote);
        //3. 읽음처리 업데이트
        voteReadUpdateService.updateVoteRead(vote);
        //4. 투표 결과 보여주기
        return voteBoardQueryService.getVoteDetails(voteId);
    }

}
