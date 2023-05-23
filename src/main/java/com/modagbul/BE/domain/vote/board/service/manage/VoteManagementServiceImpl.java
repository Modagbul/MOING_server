package com.modagbul.BE.domain.vote.board.service.manage;

import com.modagbul.BE.domain.vote.board.dto.VoteDto;
import com.modagbul.BE.domain.vote.board.entity.Vote;
import com.modagbul.BE.domain.vote.board.repository.VoteRepository;
import com.modagbul.BE.domain.vote.board.service.validate.VoteValidationService;
import com.modagbul.BE.domain.vote.content.service.VoteContentService;
import com.modagbul.BE.domain.vote.read.service.VoteReadService;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteManagementServiceImpl implements VoteManagementService {

    private final VoteValidationService voteValidationService;
    private final VoteContentService voteContentService;
    private final VoteReadService voteReadService;
    private final VoteRepository voteRepository;

    @Override
    public VoteDto.GetVoteDetailsResponse doVote(Long teamId, Long voteId, VoteDto.DoVoteRequest doVoteRequest) {
        //1. 유효성 체크
        Vote vote = voteValidationService.validateVote(teamId, voteId);
        //2. 투표 선택지 업데이트
        voteContentService.updateVoteContent(doVoteRequest, vote);
        //3. 읽음처리 업데이트
        voteReadService.updateVoteRead(vote);
        //4. 투표 결과 보여주기
        return voteRepository.getVoteDetailByVoteId(voteId);
    }


    @Override
    public void closeVote(Long teamId, Long voteId) {
        Vote vote = voteValidationService.validateVote(teamId, voteId);
        voteValidationService.validateWriter(SecurityUtils.getLoggedInUser(), vote);
        vote.closeVote();
    }

}
