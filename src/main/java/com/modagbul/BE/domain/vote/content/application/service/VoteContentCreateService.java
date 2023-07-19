package com.modagbul.BE.domain.vote.content.application.service;

import com.modagbul.BE.domain.vote.board.application.dto.req.VoteRequest.CreateVoteRequest;
import com.modagbul.BE.domain.vote.board.domain.entity.Vote;
import com.modagbul.BE.domain.vote.content.domain.entity.VoteContent;
import com.modagbul.BE.domain.vote.content.domain.service.VoteContentSaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteContentCreateService {
    private final VoteContentSaveService voteContentSaveService;

    public void createVoteContent(CreateVoteRequest createVoteRequest, Vote vote) {
        List<String> contents = createVoteRequest.getChoices();
        contents.stream().forEach(content -> {
            VoteContent voteContent = new VoteContent();
            voteContent.setContent(content);
            voteContent.setVote(vote);
            voteContentSaveService.saveVoteContent(voteContent);
        });
    }
}
