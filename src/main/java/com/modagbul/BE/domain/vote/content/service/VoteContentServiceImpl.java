package com.modagbul.BE.domain.vote.content.service;

import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.domain.vote.board.dto.VoteDto;
import com.modagbul.BE.domain.vote.board.entity.Vote;
import com.modagbul.BE.domain.vote.board.exception.NotFoundVoteContentException;
import com.modagbul.BE.domain.vote.content.entity.VoteContent;
import com.modagbul.BE.domain.vote.content.repository.VoteContentRepository;
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
public class VoteContentServiceImpl implements VoteContentService{

    private final VoteContentRepository voteContentRepository;
    private final VoteContentUserRepository voteContentUserRepository;
    private final UserRepository userRepository;

    /**
     * 투표 선택지를 저장하는 메서드
     *
     * @param createVoteRequest
     */
    public void createVoteContent(VoteDto.CreateVoteRequest createVoteRequest, Vote vote) {
        List<String> contents = createVoteRequest.getChoices();
        contents.stream().forEach(content -> {
            VoteContent voteContent = new VoteContent();
            voteContent.setContent(content);
            voteContent.setVote(vote);
            voteContentRepository.save(voteContent);
        });
    }

    /**
     * 투표할 때 투표 선택지를 업데이트하는 메서드 : 이때 해당 유저가 기존에 투표했던거는 삭제
     *
     * @param doVoteRequest
     */
    public void updateVoteContent(VoteDto.DoVoteRequest doVoteRequest, Vote vote) {
        //해당 유저가 기존에 투표했던 거 삭제
        voteContentUserRepository.deleteAllByUser(SecurityUtils.getLoggedInUser());
        //투표한 사람 업데이트
        List<String> contents = doVoteRequest.getChoices();
        contents.stream().forEach(content -> {
            VoteContent voteContent = voteContentRepository.findByContentAndVote(content, vote).orElseThrow(() -> new NotFoundVoteContentException());
            VoteContentUser voteContentUser = new VoteContentUser();
            voteContentUser.setVoteContent(voteContent);
            voteContentUser.setUser(userRepository.findById(SecurityUtils.getLoggedInUser().getUserId()).orElseThrow(() -> new NotFoundEmailException()));
            voteContentUser.setVote(vote);
            voteContentUserRepository.save(voteContentUser);
        });
    }
}
