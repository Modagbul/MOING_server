package com.modagbul.BE.domain.vote.board.service.info;

import com.modagbul.BE.domain.vote.board.dto.VoteDto;
import com.modagbul.BE.domain.vote.board.entity.Vote;
import com.modagbul.BE.domain.vote.board.repository.VoteRepository;
import com.modagbul.BE.domain.vote.board.service.validate.VoteValidationService;
import com.modagbul.BE.domain.vote.read.service.VoteReadService;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteInfoServiceImpl implements VoteInfoService {

    private final VoteValidationService voteValidationService;
    private final VoteReadService voteReadService;
    private final VoteRepository voteRepository;

    @Override
    public VoteDto.GetVoteDetailsResponse getVoteDetail(Long teamId, Long voteId) {
        //1. 유효성 체크
        Vote vote = voteValidationService.validateVote(teamId, voteId);
        //2. 읽음처리 업데이트
        voteReadService.updateVoteRead(vote);
        //3. 투표 조회
        return voteRepository.getVoteDetailByVoteId(voteId);
    }

    @Override
    public VoteDto.GetVoteAllResponse getVoteAll(Long teamId) {
        return voteRepository.getVoteAllByTeamId(teamId, SecurityUtils.getLoggedInUser().getUserId());
    }

    @Override
    public List<VoteDto.GetUnReadVoteResponse> getUnReadVote(Long teamId) {
        return voteRepository.getUnReadVoteByTeamId(teamId, SecurityUtils.getLoggedInUser().getUserId());
    }


}
