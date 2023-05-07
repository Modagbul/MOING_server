package com.modagbul.BE.domain.vote.board.service;

import com.modagbul.BE.domain.notice.board.entity.Notice;
import com.modagbul.BE.domain.notice.board.exception.NotNoticeWriterException;
import com.modagbul.BE.domain.team.entity.Team;
import com.modagbul.BE.domain.team.service.TeamService;
import com.modagbul.BE.domain.team_member.entity.TeamMember;
import com.modagbul.BE.domain.team_member.repository.TeamMemberRepository;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.domain.vote.board.dto.VoteDto.*;
import com.modagbul.BE.domain.vote.board.dto.VoteMapper;
import com.modagbul.BE.domain.vote.board.entity.Vote;
import com.modagbul.BE.domain.vote.board.exception.NotFoundVoteContentException;
import com.modagbul.BE.domain.vote.board.exception.NotFoundVoteIdException;
import com.modagbul.BE.domain.vote.board.exception.NotFoundVoteUserException;
import com.modagbul.BE.domain.vote.board.repository.VoteRepository;
import com.modagbul.BE.domain.vote.content.entity.VoteContent;
import com.modagbul.BE.domain.vote.content.repository.VoteContentRepository;
import com.modagbul.BE.domain.vote.content.user.enttiy.VoteContentUser;
import com.modagbul.BE.domain.vote.content.user.repository.VoteContentUserRepository;
import com.modagbul.BE.domain.vote.read.entity.VoteRead;
import com.modagbul.BE.domain.vote.read.repository.VoteReadRepository;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class VoteServiceImpl implements VoteService{

    private final VoteMapper voteMapper;
    private final VoteRepository voteRepository;
    private final VoteContentRepository voteContentRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final VoteReadRepository voteReadRepository;
    private final UserRepository userRepository;
    private final VoteContentUserRepository voteContentUserRepository;

    private final TeamService teamService;

    @Override
    public CreateVoteResponse createVote(Long teamId, CreateVoteRequest createVoteRequest) {
        //1. 투표  생성, 저장
        Vote vote=voteMapper.toEntity(teamId, createVoteRequest);
        voteRepository.save(vote);

        //2. 투표 선택지 저장
        createVoteContent(createVoteRequest, vote);

        //3. 투표-읽음 db 생성
        createVoteRead(teamId, vote);
        return new CreateVoteResponse(vote.getVoteId());
    }

    @Override
    public void doVote(Long teamId, Long voteId, DoVoteRequest doVoteRequest) {
        //1. 유효성 체크
        Vote vote=validateVote(teamId, voteId);
        //2. 투표 선택지 업데이트
        updateVoteContent(doVoteRequest, vote);
        //3. 읽음처리 업데이트
        updateVoteRead(vote);
    }

    @Override
    public GetVoteDetailsResponse getVoteDetail(Long teamId, Long voteId) {
        //1. 유효성 체크
        Vote vote=validateVote(teamId, voteId);
        //2. 읽음처리 업데이트
        updateVoteRead(vote);
        //3. 투표 조회
        return voteMapper.toDto(vote, voteReadRepository.getNotReadUsersNickName(voteId), mappingFromVoteContent(vote, vote.getVoteContents()));
    }

    @Override
    public void closeVote(Long teamId, Long voteId) {
        Vote vote=validateVote(teamId, voteId);
        validateUser(SecurityUtils.getLoggedInUser(),vote);
        vote.closeVote();
    }

    /**
     * 투표가 종료되었는지 확인하는 메서드 (유효성 체크 메서드)
     * @param voteId
     * @return 투표가 종료되지 않으면 Vote 반환
     */

    @Override
    public Vote validateVote(Long teamId, Long voteId){
        Team team=teamService.validateTeam(teamId);
        return this.voteRepository.findNotClosedByVoteId(voteId).orElseThrow(()->new NotFoundVoteIdException());
    }

    /**
     * 투표 선택지를 저장하는 메서드
     * @param createVoteRequest
     */
    private void createVoteContent(CreateVoteRequest createVoteRequest, Vote vote){
        List<String> contents=createVoteRequest.getChoices();
        contents.stream().forEach(content->{
            VoteContent voteContent=new VoteContent();
            voteContent.setContent(content);
            voteContent.setVote(vote);
            voteContentRepository.save(voteContent);
        });
    }

    /**
     * 투표할 때 투표 선택지를 업데이트하는 메서드 : 이때 해당 유저가 기존에 투표했던거는 삭제
     * @param doVoteRequest
     */
    private void updateVoteContent(DoVoteRequest doVoteRequest, Vote vote){
        //해당 유저가 기존에 투표했던 거 삭제
        voteContentUserRepository.deleteAllByUser(SecurityUtils.getLoggedInUser());
        //투표한 사람 업데이트
        List<String> contents=doVoteRequest.getChoices();
        contents.stream().forEach(content->{
            VoteContent voteContent=voteContentRepository.findByContentAndVote(content, vote).orElseThrow(()->new NotFoundVoteContentException());
            VoteContentUser voteContentUser=new VoteContentUser();
            voteContentUser.setVoteContent(voteContent);
            voteContentUser.setUser(userRepository.findById(SecurityUtils.getLoggedInUser().getUserId()).orElseThrow(()->new NotFoundEmailException()));
            voteContentUserRepository.save(voteContentUser);
        });
    }


    /**
     * 투표 읽음 정보 디비를 생성하는 메소드 : 생성한 사람은 무조건 읽음 처리
     * @param teamId, vote
     */
    private void createVoteRead(Long teamId, Vote vote){
        //읽음 정보 데이터 생성
        List<TeamMember> teamMembers = teamMemberRepository.findByTeamId(teamId);
        teamMembers.stream().forEach(teamMember -> {
            VoteRead voteRead=new VoteRead();
            voteRead.setVote(vote);
            voteRead.setUser(teamMember.getUser());
            voteReadRepository.save(voteRead);
        });
        //생성한 사람 읽음 처리
        updateVoteRead(vote);
        //투표 안읽은 사람 알림 가기 (전체 소모임원에서 생성한 사람 빼면 !) -> sendMultipleDevices() 함수 이용하면 될 듯
    }

    /**
     * 지금 현재 유저를 읽음 처리하는 메서드
     * @param vote
     */

    private void updateVoteRead(Vote vote){
        VoteRead voteRead=voteReadRepository.findByUserAndVote(userRepository.findById
                                (SecurityUtils.getLoggedInUser().getUserId()).orElseThrow(()->new NotFoundEmailException()),
                        vote)
                .orElseThrow(()-> new NotFoundVoteUserException());
        voteRead.readVote();
    }

    /**
     * From List<VoteContent> to List<VoteChoice>
     * @param voteContents
     * @return voteChoices
     */
    private List<VoteChoice> mappingFromVoteContent(Vote vote, List<VoteContent> voteContents){
        List<VoteChoice> voteChoiceList=new ArrayList<>();

        voteContents.stream().forEach(voteContent->{
            String content=voteContent.getContent();
            List<String> usersNickName=voteContentUserRepository.getUsersNickNameByContent(vote, content);
            VoteChoice voteChoice=new VoteChoice(content, usersNickName.size(), usersNickName);
            voteChoiceList.add(voteChoice);
        });

        return voteChoiceList;
    }

    /**
     * 투표를 작성한 유저인지 확인하는 메서드
     * @param user
     * @param vote
     */

    private void validateUser(User user, Vote vote){
        if(vote.getUser().getUserId()!=user.getUserId())
            throw new NotNoticeWriterException();
    }
}
