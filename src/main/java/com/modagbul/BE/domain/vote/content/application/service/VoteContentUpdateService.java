package com.modagbul.BE.domain.vote.content.application.service;

import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.domain.vote.board.application.dto.req.VoteRequest.DoVoteRequest;
import com.modagbul.BE.domain.vote.board.domain.entity.Vote;
import com.modagbul.BE.domain.vote.content.domain.entity.VoteContent;
import com.modagbul.BE.domain.vote.content.domain.service.VoteContentQueryService;
import com.modagbul.BE.domain.vote.content.user.enttiy.VoteContentUser;
import com.modagbul.BE.domain.vote.content.user.repository.VoteContentUserRepository;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteContentUpdateService {

    private final VoteContentUserRepository voteContentUserRepository;
    private final VoteContentQueryService voteContentQueryService;
    private final UserRepository userRepository;

    public void updateVoteContent(DoVoteRequest doVoteRequest, Vote vote) {
        //해당 유저가 기존에 투표했던 거 삭제
        voteContentUserRepository.deleteAllByUser(SecurityUtils.getLoggedInUser());
        //투표한 사람 업데이트
        List<String> contents = doVoteRequest.getChoices();
        contents.stream().forEach(content -> {
            VoteContent voteContent = voteContentQueryService.getVoteContent(content, vote);
            VoteContentUser voteContentUser = new VoteContentUser();
            voteContentUser.setVoteContent(voteContent);
            voteContentUser.setUser(userRepository.findById(SecurityUtils.getLoggedInUser().getUserId()).orElseThrow(() -> new NotFoundEmailException()));
            voteContentUser.setVote(vote);
            voteContentUserRepository.save(voteContentUser);
        });
    }
}
