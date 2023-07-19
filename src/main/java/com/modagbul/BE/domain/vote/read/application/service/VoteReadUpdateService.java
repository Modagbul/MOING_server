package com.modagbul.BE.domain.vote.read.application.service;

import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.domain.vote.board.domain.entity.Vote;
import com.modagbul.BE.domain.vote.read.domain.entity.VoteRead;
import com.modagbul.BE.domain.vote.read.domain.service.VoteReadQueryService;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteReadUpdateService {
    private VoteReadQueryService voteReadQueryService;
    private final UserRepository userRepository;

    public void updateVoteRead(Vote vote) {
        VoteRead voteRead = voteReadQueryService.getVoteRead(userRepository.findById
                        (SecurityUtils.getLoggedInUser().getUserId()).orElseThrow(() -> new NotFoundEmailException()),
                vote);
        voteRead.readVote();
    }
}
