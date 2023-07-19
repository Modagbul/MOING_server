package com.modagbul.BE.domain.vote.board.application.service;

import com.modagbul.BE.domain.vote.board.application.dto.res.VoteResponse;
import com.modagbul.BE.domain.vote.board.domain.entity.Vote;
import com.modagbul.BE.domain.vote.board.domain.service.VoteBoardQueryService;
import com.modagbul.BE.domain.vote.read.application.service.VoteReadUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteBoardReadService {
    private final VoteBoardQueryService voteBoardQueryService;
    private final VoteValidateService voteValidateService;
    private final VoteReadUpdateService voteReadUpdateService;

    public VoteResponse.GetVoteDetailsResponse getVoteDetail(Long teamId, Long voteId) {
        //1. 유효성 체크
        Vote vote = voteValidateService.validateVote(teamId, voteId);
        //2. 읽음처리 업데이트
        voteReadUpdateService.updateVoteRead(vote);
        //3. 투표 조회
        return voteBoardQueryService.getVoteDetails(voteId);
    }

    public VoteResponse.GetVoteAllResponse getVoteAll(Long teamId) {
        return voteBoardQueryService.getVoteAll(teamId);
    }


    public List<VoteResponse.GetUnReadVoteResponse> getUnReadVote(Long teamId) {
        return voteBoardQueryService.getUnReadVote(teamId);
    }
}
