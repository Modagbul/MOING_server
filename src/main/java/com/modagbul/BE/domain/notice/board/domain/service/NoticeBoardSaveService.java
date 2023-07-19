package com.modagbul.BE.domain.notice.board.domain.service;

import com.modagbul.BE.domain.notice.board.domain.entity.Notice;
import com.modagbul.BE.domain.notice.board.domain.repository.NoticeBoardRepository;
import com.modagbul.BE.global.annotation.DomainService;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@DomainService
@Transactional
@RequiredArgsConstructor
public class NoticeBoardSaveService {
    private final NoticeBoardRepository noticeBoardRepository;

    public void saveNoticeBoard(Notice notice){
        noticeBoardRepository.save(notice);
    }
}
