package com.modagbul.BE.domain.vote.board.service.validate;

import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.team.service.validate.TeamValidationService;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.vote.board.entity.Vote;
import com.modagbul.BE.domain.vote.board.exception.NotFoundVoteIdException;
import com.modagbul.BE.domain.vote.board.exception.NotVoteWriterException;
import com.modagbul.BE.domain.vote.board.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteValidationServiceImpl implements VoteValidationService{

    private final TeamValidationService teamValidationService;
    private final VoteRepository voteRepository;


    /**
     * 투표가 종료되었는지 확인하는 메서드 (유효성 체크 메서드)
     *
     * @param voteId
     * @return 투표가 종료되지 않으면 Vote 반환
     */
    @Override
    public Vote validateVote(Long teamId, Long voteId) {
        Team team = teamValidationService.validateTeam(teamId);
        return this.voteRepository.findNotClosedByVoteId(voteId).orElseThrow(() -> new NotFoundVoteIdException());
    }


    @Override
    public void validateWriter(User user, Vote vote) {
        if (vote.getUser().getUserId() != user.getUserId())
            throw new NotVoteWriterException();
    }
}
